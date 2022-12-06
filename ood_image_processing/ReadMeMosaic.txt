The way we implemented Mosaic was by implementing ImageProcessingCommand with a command object
called Mosaicking. All the functionality of the mosiac is inside this command object.

Inside the ControllerFeatures class, we added a new command, Mosaicking, to their command map. This
mapped a command string to a Function object, which took in some input and outputted an image. We
implemented it this way to uphold the command design pattern they had. Furthermore, we directly
edited the map due to the difficulty of extending their controller. While they had a command map,
they had a base controller features that was implemented by four other controller classes. In order,
to extend the features class, we would have had to make a new features class, as well as duplicate
all the old controllers to extend the new features class. After a discussion with Vidoje, we
decided to do the one line fix of adding the command to the existing command map.

a line to include the command name which
maps to a Function of Scanner -> Mosaicking.

Then in the view, we added a button that