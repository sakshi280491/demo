# RogETMF
This project contains a series of tutorials about building EMC D2 Widgets using GXT.
It also aims to provide a set of generic, helper projects, which can be used as a foundation for building environment independent, easy to maintain widgets for D2.

This tutorials are also a case study of advanced D2 customization topics like:
* Using FreeMarker syntax in Dynamic controls, Public searches and Quick search
* Using Open Ajax Hub as gateway to D2FS and DFC based services
* Building a custom GXT tree using BrowserContentD2fsService

## Download
This project is published incrementally, adding one tutorial after another. Eventually it will contain all modules necessary to create the most advanced component, described in the last tutorial. 

## Documentation
A primary source of information are the tutorials published on EMC Community network:
* [Tutorial1][]
* [Tutorial2][]
* [Tutorial3][]

See also the current [Javadoc][].

## Building from Source
Project consists of multiple maven modules, which combined form a frontend and backend for a D2 widget.
Depending on your needs or tutorial which you want to study, some modules are mandatory and some are optional:

Mandatory modules:
* rog-publicsearches-oah - JSNI wrapper for D2 Open Ajax Hub.

Optional modules:
* rog-publicsearches-d2fs - Container for D2FS services generated client. The only custom code in this module is the ModelPortServiceFactory class, which allows to fetch service from any remote d2fs.wsld and supports caching of the service instance.

Tutorial modules:
* rog-publicsearches-d2-api - provides abstract classes for interrogating with D2:
  * AbstractD2fsService - service class providing access to D2FS services
  * AbstractCallbackAction - provides schema for communication with Open Ajax Hub (OAH) triggered by D2_ACTION_EXECUTE event
  * AbstractWebfsService - service class providing access to repository via DFC
* rog-publicsearches-plugin - this module provides sample classes used in the tutorials, based on the abstract classes described above
* rog-tutorial-one-widget - demonstrates how to use rog-publicsearches-oah in a simple GXT project
* rog-publicsearches-widget - contains the code for the most advanced, last tutorial - how to create a custom D2 Public searches widget. Takes advantage of rog-publicsearches-d2-api. This module is exposing services, which can be utilized by the widget via 

### Prerequisites
This project does not contain following components, which are not owned nor supported by Roche:
* D2 Open Ajax Hub
* GWT and GXT (Sencha ext-GWT)
* D2FS client classes

### Check out sources
`git clone git@github.com:Roche/ROGeTMF.git`

### Compile and test
* D2 Open Ajax Hub - D2-OAH.js and OpenAjaxManagedHub-all.js must be copied to `src\main\webapp\js` directory of GXT widget projects (README.md placeholder included)
* GXT resources like
  * chart
  * css
  * flash
  * images
  * themes
  
  must be copied to `src\main\webapp\gxt-resources` directory of GXT widget projects (README.md placeholder included).
* D2FS client classes have to be generated and copied to `src\main\java` of rog-publicsearches-d2fs. If as a result, any existing file gets overwritten, please update it to the version downloaded from the project repository. According to D2 Architecture White paper to generate the set of classes one should execute `wsimport -keep http://localhost:8080/D2/ws/d2fs.wsdl`

## License
This project is released under version 2.0 of the Apache License.

* EMC Documentum D2, DFC, D2FS, D2 Open Ajax Hub are registered trademarks of EMC Corporation.
* Google Web Toolkit (GWT) is a registered trademark of Google Inc.
* GXT (Ext-GWT) is a registered trademark of Sencha Inc.

Creator(s) of this project claims no intellectual property rights over this libraries nor take responsibility for any kind of damage caused by using it.

[Tutorial1]:http://community.emc.com/people/KJurkowski/blog/2015/03/17/gxt-d2-widgets--part-1
[Tutorial2]:http://community.emc.com/people/KJurkowski/blog/2015/06/19/dynamic-controls--gxt-d2-widgets--part-2
[Tutorial3]:https://community.emc.com/people/KJurkowski/blog/2015/10/19/public-searches--gxt-d2-widgets--part-3
[Javadoc]:http://roche.github.io/ROGeTMF/javadoc/
