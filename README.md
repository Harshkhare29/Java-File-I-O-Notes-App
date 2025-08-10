# Java-File-I-O-Notes-App
Java File I/O is fundamental for developing a notes application, as it enables the application to store and retrieve note data persistently. The java.io package provides classes for handling input and output operations, including file manipulation.
Reading Data (Loading Notes):
FileReader and BufferedReader: For reading text-based notes, FileReader reads character streams from a file, and BufferedReader buffers characters for efficient reading, especially line by line using readLine().
FileInputStream and ObjectInputStream: For reading serialized Java objects (e.g., a Note object containing title, content, and timestamp), FileInputStream reads byte streams from a file, and ObjectInputStream deserializes objects from that stream.
Writing Data (Saving Notes):
FileWriter and BufferedWriter: For writing text-based notes, FileWriter writes character streams to a file, and BufferedWriter buffers characters for efficient writing.
FileOutputStream and ObjectOutputStream: For writing serialized Java objects, FileOutputStream writes byte streams to a file, and ObjectOutputStream serializes objects to that stream.
Exception Handling:
File I/O operations can throw IOException (e.g., file not found, permission issues). Proper try-catch-finally blocks are crucial to handle these exceptions and ensure resources (like streams) are closed.
