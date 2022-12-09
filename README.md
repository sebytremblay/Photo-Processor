##  Design Overview:

- We have an image processor which acts as our model. It performs all the functionality on the 
images, as well as stores them. Each "image" is represented as an array of pixels and a max value.
- We have a pixel class to represent a single pixel. Our particular implementation is an RGB pixel 
that stores each of the three color components. This inherits the Pixel interface so we can use 
different types of pixels later on.
- We have the ImageProcessorController handle user inputs. This takes in a user command and 
maps it to the corresponding Function object to perform the intended functionality. We have a 
ControllerFeatures that gets data from a Features interface.
- We have a ImageProcessorView that displays the view images visually and a histogram showing the
distribution of colors.
- We have RunImageProcessor as our Main method to run the image processor.


## Image Citation:

20by5 Image was generated by using a random number generator to get values between 0-255. We thus own the image and authorize ourselves to use it. 

## Completed Parts

- Can load and save .ppm, .jpg, .jpeg, .png, and .bng files 
- Can load on file type, do work on it, and save as another file type
- Can blur and sharpen an image
- Can greyscale and sepia an image
- Can visualize red, green, blue components of an image
- Can visualize intensity, luma, and value of an image
- Can brighten and darken an image
- Can horizontally and vertically flip an image
- Can accept a script file as a command-line argument
- Can display an Image on a view
- Can display a histogram of each color component and their average(intensity)
- Can resize an image

## Design Changes for Assignment 5

- Instead of storing a maxValue map in the ImageProcessor and passing the value into each pixel method, we normalize all PPM images to 255.
- Since a lot of the methods had duplicated functionality, we abstracted a lot of the methods into function objects




## Additions for Assignment 8 - resizing commands

- added a new method in the imageProcessor - applyResize
- implemented that method in ImageProcessorModel
- created a new command for the controller for resize
- added a new button on the GUI
- Added Controller tests for resize
- Added model tests for resize

IMAGE OF CAR:
https://all-free-download.com/free-photos/500-x-500-pixel.html