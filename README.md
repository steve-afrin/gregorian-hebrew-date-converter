# Gregorian-Hebrew Date Converter

This application helps calculate Hebrew calendar dates for Gregorian calendar
dates. Specifically the motivation for this project started with wanting to
be able to determine my father's Yahrzeit years.

---

## Quickstart

Use Maven 3.2 or later to build this project as such:

```mvn clean install```

Then execute it from the command line:

```java -jar yahrzeits-calculator-spring-boot.jar -n Dad -d 1996-03-15 -t 9```

or with the longer parameter names as in:

```java -jar yahrzeits-calculator-spring-boot.jar --name Dad --date 1996-03-15 --total 9```

---

## What is a **Yahrzeit**?

[Yahrzeit](https://en.wikipedia.org/wiki/Bereavement_in_Judaism#Yahrzeit)
is a [Yiddish](https://en.wikipedia.org/wiki/Yiddish) word that refers to the
anniversary, according to the Hebrew calendar, of the day of death of a
loved one. On the anniversary of a death, it is the custom to light a candle
to commemorate the departure of a loved one.

Knowing the specific day on the Gregorian calendar that my father died, I wanted
to be able to easily and accurately determine in years going forward when to
observe my father's Yahrzeit, so this project achieves that goal.

As a developer, I have always enjoyed the challenge of dealing with times and
dates. Converting times, and especially converting dates between various calendars
(e.g., Gregorian, Hebrew, Julian, Chinese, Hindu, Persian, Assyrian, etc.), can be
a very complicated and error-prone process. There are estimated to be as many as
40 different calendar systems in use around the world today, and most of them are
for the calculation or determination of religious holidays.

---

## The Challenge

Converting dates from any one calendar to another can be very complicated.
I would actually go as far as to say it is a fool's errand to learn/understand all
the complex rules that go into the conversion from any one calendar to another and
implement and test those complex rules and behaviors. As in most complex business
domains, it is far better to use a service developed and tested by someone else or
another organization rather than having to learn/understand all the rules for
conversion between two calendars and then implementing all those rules.

The obvious caveats to using services implemented and provided by other
developers are:

  1. Service reliability - you need to know that you are using a service that
     is consistent and reliable. The service is effectively useless if it is
     frequently down or unavailable for whatever reason.
  2. Accuracy - this could arguably be considered an aspect of service reliability,
     but it is essential enough that I'm making it its own separate consideration.
     Any service is useless if the calculations or results are wrong.
  3. Cost/licensing - this may or may not be a factor depending on if there
     is a cost to use a particular service depending on your budget. Also,
     a developer using any library, framework, or service should always
     consider what license is used by that library, framework, or service to
     determine its effect on the publication or production of the software
     systems using such libraries, frameworks, and services.

---

## The [Hebrew Calendar website](https://www.hebcal.com/)

This project is based heavily on webservices provided by the maintainers of the
[Hebrew Calendar website](https://www.hebcal.com/) as part of the Source Judaism
movement:

  * [Hebrew Date Converter REST API](https://www.hebcal.com/home/219/hebrew-date-converter-rest-api)
    is designed for converting Gregorian dates to the Hebrew calendar and for
    converting Hebrew dates to the Gregorian calendar
  * [Yahrzeit + Anniversary API](https://www.hebcal.com/home/1705/yahrzeit-anniversary-api)
    is designed to take Gregorian dates and calculate one or a series of dates
    for subsequent years mapping to the specific Hebrew date of the original
    date specified in the request. _The API webpage describes this specific API
    as experimental, however, I have personally verified that the dates I have
    received from use of this API appear to be very accurate so far. But caution
    should always be taken when using results from any experimental library,
    framework, or service._

These webservice APIs are available at no cost under a [Creative Commons
Attribution 3.0 License](https://creativecommons.org/licenses/by/3.0/).

---

## Command Line Arguments

This was my first attempt using the
[Airline annotation framework](https://rvesse.github.io/airline/) to parse the
command line arguments and provide help when requested with the `--help` argument.
Any developer who has ever developed an application to take more than one parameter
knows that the parsing of arguments and parameters can quickly get complex.

  * Which arguments are required and which are optional?
  * In what order should they be specified?
  * How do you convert from `String` types on the command line to actual types in
    a strongly typed language like Java? (e.g., converting a `String` to a
    `LocalDate` object)
  * If help is requested for a specific parameter, you need to provide that output.
  * If required arguments are missing or invalid values, then you need to output
    a basic/general usage (how to use) message

Handling all those use cases can involve writing a bunch of framework code, and
quite honestly, that gets to be exhausting. Not only do you have to write that
code, but you also have to validate it, and then you now have a maintenance issue,
and it's not even directly related to the core issue of the problem you started
out to solve!

Obviously consuming an existing framework that handles these details is much,
much easier and less time-consuming, and I came across this annotation-driven
framework that is available under the
[Apache License 2.0](http://apache.org/licenses/LICENSE-2.0) license, so I would
have been remiss to not use it.

There are other frameworks to use as well (e.g.,
[picocli](https://picocli.info/),
[JCommander](http://jcommander.org/),
[Java Simple Argument Parser (JSAP)](https://sourceforge.net/projects/jsap/)),
[Apache Commons CLI](https://mvnrepository.com/artifact/commons-cli/commons-cli/1.4),
[JArgs](http://jargs.sourceforge.net/),
[JOpt Simple](https://jopt-simple.github.io/jopt-simple/), etc.), but hey,
this was a new thing for me to try, and it was somewhat painless. Like most
frameworks, the developer will get much more efficient with it as it is used
time and time again across multiple projects. I don't know that I implemented
[Airline](https://rvesse.github.io/airline/) in the best possible way for this
project, but hey, I did get it working and my limited testing with it was
encouraging. The learning curve on this framework was rather small compared
with other much more complex frameworks that I've had to learn in the past.

_Word of caution_: whatever command line argument parsing library or framework
you choose to use, check to see if it is still being actively developed or how
dated the codebase is. That should always be at least one primary
consideration for moving forward with any specific library or framework.

[Airline](https://rvesse.github.io/airline/) resonated strongly with me because
it supports simple commands as well as complex Git-style CLIs, and it's a
pretty current framework. The most recent update to the
[Airline 2.0 GitHub Repository](https://github.com/rvesse/airline) as of this
writing was in early July 2021.

Other resources I used to quickstart my learning curve for the Airline
framework:

  * [Parsing Command-Line Parameters with Airline](https://www.baeldung.com/java-airline)

---

## Project Evolution

Potential ideas to consider for future iterations of this project to enhance
its appeal, usability, and code maintainability:

  * Failure recovery and handling for webservices
  * Determining sunset at a specific time on a given date for a specific timezone
  * Improve implementation of immutable POJOs
  * Accept alternative date formats on the command line
  * Design and implement a web-based user interface
  * Use a library for date conversions/calculations instead of webservice invocations

I have not yet used any fail and retry frameworks like
[Spring Retry](https://docs.spring.io/spring-batch/docs/current/reference/html/retry.html)
or [Failsafe](https://failsafe-lib.github.io/) to handle network failures
to connect with the
[Hebrew Calendar REST API services](https://www.hebcal.com/home/developer-apis)
and recover from such failures. That would likely be the first place I'd go
back to enhance and improve this project, though fortunately the Hebrew
Calendar host seems to be rather reliable and stable so far.

I would also like to use the `ZonedDateTime` object in the
`HebrewDateConversionService#getHebrewDate(ZonedDateTime)` method to determine
whether sunset had happened or not on that day to accurately and appropriately
set the `isAfterSunset` attribute to `true` or `false` in the
`ConvertedHebrewDate` instance. For an initial go at this project, I just set
the value to `false` by default whenever creating a new `ConvertedHebrewDate`
instance. Determining exactly when sunset has happened in a specific time
zone may very well require the invocation of yet another webservice call to
some other API, which obviously just adds more complexity to the required
solution.

I also didn't really use the
[Immutables library](https://immutables.github.io/) as efficiently as I should
have. I'd love to go back and make the immutable POJOs that can be built only
via the Builder design pattern to be smaller in code and more effectively use
the annotations from the [Immutables library](https://immutables.github.io/).

Allow/accept other date formats to be accepted via the command line `--date`
parameter. The big question is here is which date formats should be accepted,
and if the design allows multiple part parameter values (the Airline
framework does support this use case), then what should that look like? This
could obviously become a very hairy thing to evolve, but it is worth
consideration at some point. Quite frankly, specifying the date is something
calendar widgets on a good user interface are really great for, which leads
to the next strong consideration for enhancing this project ... a UI!

This project could probably do well with a web-based user interface added to
support interaction with the calendar date conversion services via a webpage.
If this work were to be done, then this project could be containerized and
deployed to a cloud environment for use by anyone who actually knew (or could
discover) the web address. Should that be the highest priority for the next
iteration of this project? Probably not, but it should certainly be worth
consideration somewhere down the line.

Another potential iteration of this project might be to make the actual
conversion service use a library and not rely on an invocation of the REST API
from the Hebrew Calendar host, but currently such libraries seem very limited
if any even exist at all. Additionally, REST APIs are generally much nicer to
use because the owners of such APIs are responsible for maintaining that code
and frees the developer from having to be concerned with all the details and
complexities of exactly how the conversions are actually performed.
