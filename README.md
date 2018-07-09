[![Build Status](https://travis-ci.com/mariodavid/testery.svg?branch=master)](https://travis-ci.com/mariodavid/testery)
[![license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)

Testery - Exploratory testing with automation
======================

Efficient exploratory testing with as much automation as possible. Have you ever felt the need to give your
QA, business people as well as developers a mechanism to test your application(s) from the outside? Although you
might have test automation in place, there is little visibility in the automated test suites that live somewhere in the CI server?
Do you want to efficiently execute exploratory testing with as much automated help as possible?

Testery is a tool which allows you to exactly this. It enables business people as well as QA engineers to concentrate
on the important stuff while doing exploratory testing instead of dealing with technical details.

* your business people can create test cases with the business abstractions they know
* the execution of the test cases is done automatically via automation created by developers
* the verification phase is optimized towards your QA & business people to quickly identify the correct outcome of a testcase

## Usage

Testery can be used in two different scenarios: *standalone* or as a *library* in your customized testery-app.


### Testery as a standalone application

The first option to use testery is by just running it as is without customizations. In this case you can follow the instructions
in order to run the testery application. It has the following properties:

* testing scripts can be expressed as groovy scripts at runtime
* test step inputs are defined through the generic JSON input UI
* test step results are constrained by the existing result formats (JSON or Table)


### Testery as a library
In the second option, testery is used as an programming library in your customized testery-app. In this scenario, you have the possiblity
to extend the generic limits of the standalone testery application by:

* testing scripts can be regular classes in your testery-app
* test step inputs can be a custom data model with full DB & custom UI support
* test step results can be any kind of data and UI 


#### List of testcases
![1-testcase-browse](https://github.com/mariodavid/testery/blob/master/img/1-testcase-browse.png)

#### Testcase details
![2-testcase-editor](https://github.com/mariodavid/testery/blob/master/img/2-testcase-editor.png)

#### JSON input for test steps
![3-teststep-input](https://github.com/mariodavid/testery/blob/master/img/3-teststep-input.png)

#### Result of a test step
![4-teststep-result](https://github.com/mariodavid/testery/blob/master/img/4-teststep-result.png)

#### Testscript definition
![5-testscript-editor](https://github.com/mariodavid/testery/blob/master/img/5-testscript-editor.png)


## Installation