## Command Lists:

To start the program, you can give the jar arguments "-file <script-name>" to run a specific script.
To run using text commands you can give the jar arguments "-text".
To run the view based image processor, simply include no arguments.

If you ran via the script, the program will terminate and five you informative messages.

If you used the text version, you can use the load command "load file-path img-name", 
and then you can perform many operations on the image. Additionally, you can also save the 
image at any point by using the save command: "save
file-path img-name". 

If you are running the GUI, all buttons will appear on the left, where you can first load the image.
When you click load you will be given a new Panel that will be your directory and you can select, 
which image you would like to load.
When you load the image, this image is now stored in the program, and you can perform edits on this
image. The button commands are listed on the left and they directly edit the loaded image.
All image process commands are a single button click except for brighten which takes in a value from the text
field next to that particular button. You may enter an integer.
When you want to save this image, you click save and then you will have a panel that will appear, 
where you can specify where you want to save this image in you computer.


Valid file formats include: bmp, jpg, jpeg, png, and ppm.


List of the commands for the Terminal Based Image Processor:

Loads the image at the file path as the given name  
load file-path img-name

Saves the image to the given file path  
save file-path img-name

Visualizes the red component of the image and saves as new image name  
red-component img-name new-img-name

Visualizes the green component of the image and saves as new image name  
green-component img-name new-img-name

Visualizes the blue component of the image and saves as new image name   
blue-component img-name new-img-name

Visualizes the luma component of the image and saves as new image name  
luma-component img-name new-img-name

Visualizes the intensity component of the image and saves as new image name  
intensity-component img-name new-img-name

Visualizes the value component of the image and saves as new image name  
value-component img-name new-img-name

Horizontally flips the provided image and saves it's as the new one  
horizontal-flip img-name new-img-name

Vertically flips the provided image and saves it's as the new one  
vertical-flip img-name new-img-name

Brightens the image by the provided amount and saves it as new image  
brighten brighten-by img-name new-img-name  

Sharpens the image and saves it as a new image
sharpen img-name new-imag-name

Blurs the image and save it as a new image
blur img-name new-img-name

Visualizes the image in Greyscale and save it as a new image
greyscale img-name new-img-name

Visualizes the image in sepia and save it as a new image
sepia img-name new-img-name

Resizes the image and saves it as a new image (must be same size or smaller than original image dimensions)
resize width height img-name new-img-name
