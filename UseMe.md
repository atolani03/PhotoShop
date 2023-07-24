## List of Commands you can run on the program
1. load - Type res/ and give it a ppm image and a new file name
2. save - Give it a destination name starting with res/ and ending with .ppm and the final you want saved
    1. You do have to quit the program to see your saved files in the folder
3. convert - give it the file you want converted, the new ending, and the file name
    1. this method will convert the file to the new type and save it
    2. ex. koala.ppm jpeg koala.jpeg
4. vertical-flip - Give it an image you want flipped and the new final name
    1. Flips the image vertically
5. horizontal-flip - Give it an image you want changed and the new final name
    1. Flips the image horizontally
6. green-component - Give it an image you want changed and the new final name
    1. Sets the green component as all the components
7. red-component - Give it an image you want changed and the new final name
    1. Sets the red component as all the components
8. blue-component - Give it an image you want changed and the new final name
    1. Sets the blue component as all the components
9. luma-component - Give it an image you want changed and the new final name
    1. Uses the weighted sum (0.2126)red + (0.7512)green + (0.0722)blue
10. intensity-component - Give it an image you want changed and the new final name
    1. Sets the components as the average of all three components
11. value-component - Give it an image you want changed and the new final name
    1. the maximum value of the three components for each pixel
12. brighten - Give factor brightness, file you want brightened, and new file name for updated file
    1. brightens the image by the factor brightness
    2. darkens the image if given a negative number
13. blur - Give it an image you want blurred, and the new file name for the updated file
    1. Blurs the given image
14. sharpen - Give it an image you want sharpened, and the new file name for the updated file
    1. Sharpens the given image
15. sepia - Give it an image you want changed, and the new file name for the updated file
    1. uses the sepia matrix to change the color to a sepia tone
16. greyscale - Give it an image you want changed, and the new file name for the updated file
    1. uses the same formula as luma
17. text - give it a text file that includes a proper script of commands
    1. if commands aren't perfect there will be an error thrown and the program won't run
    2. ex. make sure the commands are lowercase
18. quit - quits the program

### Some conditions that apply
1. Must load an image before you can make any changes
   1. Changes won't work unless an image has been loaded
2. Commands must be in lowercase for them to work

### How to use the GUI model
1. Press open a file to open a file for the project
2. Use any of the commands to modify/edit the project
3. Inside the color component you will find all the different ways you can change the color of the image
4. Where it says null, use that to vertically and horizontally flip the image
   1. Once you have flipped it press it again to flip it back
   2. Don't press null! that will not revert it
5. Try downscaling the image
   1. Give the image the new width
   2. Give the image the new height
6. Finally once you are done editing your photo save it
   1. Press save and type the new save as well as the extension and the file will save
7. Once your done saving your photo exit the program simply by clicking the red button in the browser