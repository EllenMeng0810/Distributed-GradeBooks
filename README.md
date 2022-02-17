# Distributed-GradeBooks

Design and implement DGB (Distributed GradeBooks), a distributed server-side implementation of GradeBook which a web application to administrate student grades. A GradeBook is a collection of student grades for one subject. A different subject will require a separate GradeBook. 

Create 2 projects in Netbeans: one for the primary server and the other one for the secondary server.  

Both READ & WRITE operations for student grades can be done on the primary server.

Only READ operations for student grades can be done on the secondary server with the following exceptions:

Add a secondary server for a GradeBook

Primary actor: administrator (using an admin client)
Main success scenario:
Admin client requests creation of a secondary copy and supplies the GradeBook ID.
The server that receives the request validates the GradeBook ID.
The server sets up a secondary copy of the GradeBook.
Major alternative scenarios:
Failure: There is no GradeBook with the given ID.
Failure: The server already has a secondary copy of the GradeBook.
Failure: The server is the primary server for the GradeBook.
 

Remove a secondary server for a Gradebook
Primary actor: administrator (using an admin client)
Main success scenario:
Admin client requests deletion of a secondary copy and supplies the GradeBook ID.
The server that receives the request validates the GradeBook ID.
The server removes its secondary copy of the GradeBook.
Major alternative scenarios:
Failure: There is no GradeBook with the given ID.
Failure: The server does not have a secondary copy of the GradeBook.
Failure: The server is the primary server for the GradeBook.
 
