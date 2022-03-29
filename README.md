# Early Validation of Functional Requirements Through Code Synthesis
## Nestor Catano (nestor.catano@gmail.com)

This repository describes our experiences in using formal methods for the early validation of functional software requirements. We encode software requirements as User-Stories, which we use to create an Event-B formal model for the requirements. The Event-B formalization and verification are conducted with Rodin IDE version 3.3. We use the EventB2Java code generator to generate code for the Event-B model of our software requirements, then we manually write JUnit tests to check our understanding of the requirements. We finally run the tests and eventually find bugs in the tests thus in the requirements.  

**autonomous_vehicle.zip** is the Event-B project of the autonomous vehicle. You can import it from Rodin 3.3 via File, Import, Existing Project into Work Space.

**communication_system.zip** is the Event-B project of the autonomous vehicle. You can import it in Rodin 3.3 following the same procedure as above.

**autonomous_vehicle_eclipse.zip** is the Eclipse project with the unit-tests for the autonomous vehicle. Your can import it as en Eclipse project from any Java-based Eclipse IDE.

**comm_sys_US.rtf** is an .rtf document with the User Stories of the communication system.

**comm_sys_EB.rtf** is an .rtf document with the "manual" Event-B modeling of the User Stories for the communication system.

We have implemented a Natural Language Processiing (NLP) approach to convert User Stories into Event-B models. The implemented tool is available from https://bitbucket.org/seprolab/code-synthesis/src/master/
