
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
[![Build Status](https://travis-ci.com/jsoagger/jsoagger-bridge.svg?branch=master)](https://travis-ci.com/jsoagger/jsoagger-bridge)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)


# Overview

JSoagger Bridge regroups oparations for loading data from backend services or legacy services. JSoagger Bridge contains as many operations as exposed REST service in JSoagger Service.

It uses [OkHttp](http://square.github.io/okhttp/) to consume remote REST services.

&nbsp;

<p align="center">
	<a href="#"><img src="https://github.com/rmvonji/jsoagger-screenshots/blob/master/JSoagger%20Bridge.jpg" height="280"></a>
</p>

# Example

A promote lifecyle operation

```Java
public class PromoteOperation implements IOperation {


  /**
   * Constructor
   */
  public PromoteOperation() {
    super();
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public void doOperation(JsonObject params,
      Consumer<IOperationResult> resultHandler,
      Consumer<Throwable> exHandler) {
    try {
      IOperationResult result = CloudServicesLocator.lifecycleManagedApi.promote(params);
      resultHandler.accept(result);
    }
    catch (Exception e) {

      exHandler.accept(e);
    }
  }
}
```


And LifecyleManagedApi cloud services REST call:

```Java
public IOperationResult promote(JsonObject query) {
    try {
      String oid = query.get("fullId").getAsString();
      String byPathUrl = rootUrl.concat(String.format(PROMOTE_URL, oid));
      IOperationResult result = doPost(query, byPathUrl, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }
```


# Project

## Contributing  
[![contributions welcome](https://img.shields.io/badge/contributions-welcome-brightgreen.svg?style=flat)](https://github.com/dwyl/esta/issues)

Please read [CONTRIBUTING.md] for details on our code of conduct, and the process for submitting pull requests to us.

## Authors
* **rmvonji** - *Initial work* - [JSoagger ](https://github.com/jsoagger/)
* **Contact** - *yvonjisoa@nexitia.com* 

## Licence
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

This project is licensed under Apache Licence V2.

