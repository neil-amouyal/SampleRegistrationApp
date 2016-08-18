# SampleRegistrationApp
Sample Registration Application

This application was designed for a coding challenge given during the employment application process.

Please read the javadocs to learn about how to use the individual classes or see the inline and pre-method comments.

Data is entered into a form found on index.html, submitting sends the data to a servlet  which sends back a plain text confirmation. (This could send back a json object with a status report so the entry can occur asynchronously but I'm keeping it simple here due to time constraints.)

An admin section has been provided to see the data that was entered through the form in a simple html table. This is a java server page called registeredUserReport.jsp