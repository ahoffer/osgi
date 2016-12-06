Set a variable:
    >blah = 42
    >$blah
    >42


Cool command line stuff. Get a service provider:
    service:get funservices.EnumerationDictionaryService


Use a service provider:
    srv=service:get funservices.EnumerationDictionaryService
    $srv somePublicMethod param1 param2


Get the context (bundle 0):
    $.context


Get all the commands
    $.commands


Get all the variables
    $.variables