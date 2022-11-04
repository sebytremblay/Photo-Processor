# cs3500
Repository for Dilan and I's cs3500 homeworks. 

Command Lists:

// Loads the image at the file path as the given name
load file-path img-name

// Saves the image to the given file path
save file-path img-name

// Visualizes the red component of the image and saves as new image name
red-component img-name new-img-name

// Visualizes the green component of the image and saves as new image name
green-component img-name new-img-name

// Visualizes the blue component of the image and saves as new image name
blue-component img-name new-img-name

// Visualizes the luma component of the image and saves as new image name
luma-component img-name new-img-name

// Visualizes the intensity component of the image and saves as new image name
intensity-component img-name new-img-name

// Visualizes the value component of the image and saves as new image name
value-component img-name new-img-name

// Horizontally flips the provided image and saves its as the new one
horizontal-flip img-name new-img-name

// Vertically flips the provided image and saves its as the new one
vertical-flip img-name new-img-name

// Brightens the image by the provided amount and saves it as new image
brighten brighten-by img-name new-img-name



Image Citation:

This website is where we obtained the image of the flowers
https://www.usna.edu/Users/cs/choi/ic210/project/p01/index.html



Design Overview:

We have an image processor which acts as our model. It pefroms all the functionality on the images, as well as stores them. Each "image" is represented as an array of pixels and a max value.

We have a pixel class to represent a single pixel. Our particular implementation is an RGB pixel that stores each of the three color components. This inherits the Pixel interface so we can use different types of pixels later on.

We have the ImageProcessorControllerImp to handle user inputs. This takes in a user command and maps it to the corresponding Function object to perform the intended functionality.

We have RunImageProcessor as our Main method to run the image processor.

