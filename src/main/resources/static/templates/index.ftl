<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Typeahead Example with Apache Geode, Spring Boot, and Freemarker</title>
        <link href="css/bootstrap.min.css" rel="stylesheet" />
        <link href="css/jumbotron-narrow.css" rel="stylesheet" />
        <link href="css/typeahead.css" rel="stylesheet" />
        <script type="text/javascript" src="js/jquery-1.10.2.min.js" ></script>
        <script type="text/javascript" src="js/typeahead.bundle.js" ></script>
        <script type="text/javascript" src="js/handlebars.js" ></script>
    </head>

    <body >
      <div class="jumbotron">
        <h1>Welcome!</h1>
        <p></p>
        <p>Please type an address:</p>
        <div id="remote">
            <input type="text" class="typeahead" placeholder="Address Search">
        </div>
      </div>
    </body>

    <script type="text/javascript">
        // The field within the JSON to show
        var displayField = 'address';
        
        var addressMatches = new Bloodhound({
            // Note, the value here should match the "display" field below
            datumTokenizer: Bloodhound.tokenizers.obj.whitespace(displayField),
            queryTokenizer: Bloodhound.tokenizers.whitespace,
            remote: {
                url: '/properties?address=#QUERY#',
                wildcard: '#QUERY'
            }
        });
    
        $('#remote .typeahead').typeahead(
        {
            minLength: 3,
            highlight: true
        }, 
        {
            source: addressMatches,
            display: displayField,
            items: 8,
            templates: {
                empty: [
                    '<div class="empty-message">',
                    'unable to find any addresses that match the current query',
                    '</div>'
                ].join('\n'),
                suggestion: Handlebars.compile("<div><strong>{{address}}</strong> – elevation: {{elevationFeet}}'</div>")
             }
        });
    </script>
</html>
