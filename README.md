# Early Validation of Functional Requirements Through Code Synthesis
This repository describes our experiences in using formal methods for the early validation of functional software requirements. We encode software requirements as User-Stories, which we use to create an Event-B formal model for the requirements. The Event-B formalization and verification are conducted with Rodin IDE version 3.3. We use the EventB2Java code generator to generate code for the Event-B model of our software requirements, then we manually write JUnit tests to check our understanding of the requirements. We finally run the tests and eventually find bugs in the tests thus in the requirements.  

**autonomous_vehicle.zip** is the Event-B project for the autonomous vehicle. You can import it in Rodin 3.3 through File, Import, Existing Project into Work Space.

**communication_system.zip** is the Event-B project for the autonomous vehicle. You can import it in Rodin 3.3 following the same procedure as above.

**autonomous_vehicle_eclipse.zip** is the Java Eclipse project with the JUnit tests for the autonomous vehicle. Your can impor it as en Eclipse project from any Java Eclipse IDE.
