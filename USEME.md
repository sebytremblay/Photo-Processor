## Command Lists:

To start the program, you run the main method in the RunImageProcessor. From there, you should 
load an image using the load command "load file-path img-name", and then you can perform many operations
on the image. Additionally, you can also save the image at any point by using the save command: "save
file-path img-name". 

Valid file formats include: bmp, jpg, jpeg, png, and ppm.


List of the commands:

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
