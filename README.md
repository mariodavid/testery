[![Build Status](https://travis-ci.com/mariodavid/testery.svg?branch=master)](https://travis-ci.com/mariodavid/testery)
[![license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)

Testery - Exploratory testing with automation
======================

Efficient exploratory testing with as much automation as possible

## Installation

## Usage

Testery can be used in two different scenarios: *standalone* or as a *library* in your customized application.


### Testery as a standalone application

The first option to use testery is by just running it as is without customizations. In this case you can follow the instructions
in order to run the testery application. It has the following properties:

* testing scripts can be expressed as groovy scripts at runtime
* teststep input is defined through the generic JSON input UI
* teststep results is constrained by the existing result formats (JSON or Table)


### Testery as a library
In the second option, testery is used as an programming library in your customized testery-app. In this scenario, you have the possiblity
to extend the generic limits of the standalone testery application by:

* testing scripts can be regular classes in your testery-app
* teststep input can be a custom data model with full DB & custom UI support
* teststep results can be any kind of data and UI 


#### List of testcases
![1-testcase-browse](https://github.com/mariodavid/testery/blob/master/img/1-testcase-browse.png)

#### Testcase details
![2-testcase-editor](https://github.com/mariodavid/testery/blob/master/img/2-testcase-editor.png)

#### generic Input of teststep
![3-teststep-input](https://github.com/mariodavid/testery/blob/master/img/3-teststep-input.png)

#### result of teststeo
![4-teststep-result](https://github.com/mariodavid/testery/blob/master/img/4-teststep-result.png)

#### definition of testscript
![5-testscript-editor](https://github.com/mariodavid/testery/blob/master/img/5-testscript-editor.png)


### Example usage
