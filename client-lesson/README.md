#### Karaf Shell Magic
* `services`
*  Invoke a service from the shell 
   * `_sref = $.context getServiceReference "service.EnumerationDictionary"`
   * `_srv = $.context getService $_sref`
   * `echo $_srv`
   * `$_srv lookupEvent 128`
   * `$.context ungetService $_sref`

---

(Advanced) If using a filter

```
_srefs = $.context getServiceReferences "com.example.MyServiceInterface" "(filterKey=filterValue)";
_srv = $.context getService ($_srefs 0)
$_srv getFooBar
...
_srv = $.context ungetService ($_srefs 0)
```