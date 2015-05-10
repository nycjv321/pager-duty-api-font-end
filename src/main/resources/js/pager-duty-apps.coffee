# Provides ability to trigger rest api to database sync
@synchronize = ()->
  $.simplyToast("Checking...!", 'info');
  $.ajax
    url: '/synchronize'
    dataType: 'json'
    success: (data) ->
      response = data
      console.log response
      updated = response['updated']
      if updated
        $.simplyToast("Database Updated!", 'success');
        # update page
      else
        if typeof response['message'] != 'undefined' && !response['message'] != ""
          $.simplyToast("Database Not Updated! see: " + response['message'], 'warning');
        else
          $.simplyToast("Database Not Updated!", 'info');


@create_history = () ->
  if typeof localStorage == 'undefined'
    $.simplyToast("This app depends on HTML5 Local Storage. Please update your browser!", 'error');
  else
    if localStorage['pager-duty-apps.history'] != 'undefined' && localStorage['pager-duty-apps.history'] != "[]"
      $(".nav-sidebar").append("<li><a id=\"saved_searches\" href='/saved_searches'>Saved Searches</a></li>")

#@latest_id = () ->
#
#  id = $($("tr")[1]).find("td").first().find("a").text();
#

@search_history = (search)->


@incident_search = () ->
  $("form input").keydown((event) ->
    if(event.which == 13)
      event.preventDefault()
      dest = ''
      search = encodeURIComponent($("form input").val());
      if ($("form input").val().indexOf(":") < 0)
        dest = "/incidents.json?subject=#{search}&body=#{search}"
      else
        searchArguments = $("form input").val().split(":")
        if (searchArguments[0] == "subject")
          dest = "/incidents.json?subject=#{encodeURIComponent(searchArguments[1]).trim()}"
        else if (searchArguments[0] == "body")
          dest = "/incidents.json?body=#{encodeURIComponent(searchArguments[1]).trim()}"
        else if (searchArguments[0] == "team")
          dest = "/incidents.json?team=#{encodeURIComponent(searchArguments[1]).trim()}"
        else if (searchArguments[0] == "created on")
          dest = "/incidents.json?created_on=#{encodeURIComponent(searchArguments[1]).trim()}"
        else if (searchArguments[0] == "user")
          dest = "/incidents.json?user=#{encodeURIComponent(searchArguments[searchArguments.length - 1]).trim()}"
        else
          dest = "/incidents.json?subject=#{encodeURIComponent(searchArguments[1]).trim()}&body=#{encodeURIComponent(searchArguments[1]).trim()}"


      $.ajax
        url: dest
        dataType: 'json'
        success: (data, textStatus, jqHXR) ->
          incident_updater(data)
          search_history(search)
  )

@incident_updater = (data) ->
  if data.length == 0
    $.simplyToast("No results found!", 'warning');
  else
    if $("h1").val() != "Dashboard"
      $(".main").empty();
      $(".main").append("<h1 class=\"page-header\">Dashboard</h1>");
      $(".main").append("<h2 class=\"sub-header\">Incidents</h2>");
      $(".main").append("<div class=\"table-responsive\">
          <table class=\"table table-striped\">
            <thead>
              <tr>
                <th>#</th>
                 <th>Summary</th>
                 <th>Created on</th>
                 <th>Status</th>
                 <th>Last Edited By</th>
                 <th>Team</th>
                 <th>Email</th>
                 <th>Notes</th>
              </tr>
            </thead>
            <tbody></tbody>
          </table>
        </div>
        ")
    $("table tbody tr").remove()
    for incident in data
      if typeof incident.last_state_changed_by != 'undefined'
        $("table tbody").append("<tr>
          <td><a href=\"#{incident.url}\">#{incident.incident_number}</a></td>
          <td>#{incident.summary}</td>
          <td>#{incident.created_on}</td>
          <td>#{incident.status}</td>
          <td>#{incident.last_state_changed_by.name}</td>
          <td>#{incident.team}</td>
          <td>
            <button type=\"button\" class=\"btn btn-default btn-sm email-button\">
              <span class=\"glyphicon glyphicon-envelope\" aria-hidden=\"true\"></span> Email
            </button>
          </td>
          <td>
            <button type=\"button\" class=\"btn btn-default btn-sm note-button\">
             <span class=\"glyphicon glyphicon-book\" aria-hidden=\"true\"></span> Notes
            </button>
          </td>
        </tr>"
        )
      else
        $("table tbody").append("<tr>
          <td><a href=\"#{incident.url}\">#{incident.incident_number}</a></td>
          <td>#{incident.summary}</td>
          <td>#{incident.created_on}</td>
          <td>#{incident.status}</td>
          <td>No Edits</td>
          <td>#{incident.team}</td>
          <td>
            <button type=\"button\" class=\"btn btn-default btn-sm email-button\">
              <span class=\"glyphicon glyphicon-envelope\" aria-hidden=\"true\"></span> Email
            </button>
          </td>
          <td>
            <button type=\"button\" class=\"btn btn-default btn-sm note-button\">
             <span class=\"glyphicon glyphicon-book\" aria-hidden=\"true\"></span> Notes
            </button>
          </td>
          </tr>"
        )


    if $("h3").length == 0
      $("<h3 id=\"saved_searches\"><button>Save this search</button></h3>").insertAfter("h2")


$(document).ready ->
  incident_search();
  $("#synchronize").click (event) ->
    event.preventDefault()
    synchronize()
  create_history();

  $(".main").on("click", ".note-button", () ->
    row = $(this).parent().parent();
    incident_id = $($(this).parent().parent().find("td")[0]).text();

    if ($(".main").find("#" + incident_id + "_notes").size() == 0)
      $.ajax
        url: "/incident/" + incident_id + "/notes.json"
        dataType: 'json'
        success: (data) ->
          if data.length == 0
            $.simplyToast("Incident #{incident_id} had no notes. ☹ Add some!", 'warning');
          else
            note_html = ""
            note_count = 1
            data = data.reverse()
            for note in data
              note_html = note_html + "<tr><td>" + note_count + "</td><td> " + note.created_at + "</td><td> " + note.user.name + "</td><td colspan=\"6\">" + note.note.content + "</td></tr>"
              note_count = note_count + 1;

            row.after("
            <tr id=\""+ incident_id + "_notes"+ "\" class=\"notes\"><td></td>
              <td td colspan=\"7\">
                <table class=\"table table-hover table-condensed table-bordered\">
                <tr><th>#</th><th>Timestamp</th><th>Author</th><th colspan=\"7\">Message</th></tr>
                " + note_html + "
                </table>
              </td>
            </tr>
              ")
    else
      $("#" + incident_id + "_notes").toggle();
  )

  $(".main").on("click", ".email-button", () ->
    row = $(this).parent().parent();
    incident_id = $($(this).parent().parent().find("td")[0]).text();

    if ($(".main").find("#" +incident_id + "_email").size() == 0)
      $.ajax
        url: "/incident/" + incident_id + "/email.json"
        dataType: 'json'
        success: (data) ->
          row.after("
          <tr id=\""+ incident_id + "_email"+ "\"  class=\"email\"><td></td><td td colspan=\"7\">
             <table>
               <tr><td></td><td colspan=\"7\">" + data.summary + "</td></tr>
               <tr><td></td><td colspan=\"7\">" + data.from + "</td></tr>
               <tr><td></td><td colspan=\"7\">" + data.body + "</td></tr>
            </table>
          </td></tr>
          ")
    else
      $("#" + incident_id + "_email").toggle();
  )

  $(".main").on("click", "#saved_searches button", (event) ->
    event.preventDefault()
    name = prompt("Save Query", "please label query!");
    if name != null
      pagerDutyHistory = '';
      if typeof localStorage['pager-duty-apps.history'] == 'undefined'
        pagerDutyHistory = "[]";
      else
        pagerDutyHistory = localStorage['pager-duty-apps.history'];

      history = JSON.parse(pagerDutyHistory)

    already_exists = false
    for instance in history
      instance = jQuery.parseJSON(JSON.parse(instance))
      if instance.name == name
        $.simplyToast("Search #{name} already saved!", 'warning');
        already_exists = true
        break

    if !already_exists
      history.push(JSON.stringify("{\"name\": \"" + name + "\", \"query\": \"" + $("form input").val() + "\"}"))
      localStorage['pager-duty-apps.history'] = JSON.stringify(history);
      if $("#saved_searches").length == 0
        $(".nav-sidebar").append("<li><a id=\"saved_searches\" href=\"/saved_searches\">Saved Searches</a></li>")
  )
