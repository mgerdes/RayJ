# RayJ

Having fun learning about ray tracers and implementing the ray tracer from http://raytracegroundup.com/. 

# Current Progress

Currently RayJ allows you to define planes, spheres, ambient light sources, and point light sources in your scene. Shading is done using the [Blinn-Phong Model](https://en.wikipedia.org/wiki/Blinn%E2%80%93Phong_shading_model).

Future versions hope to include reflections, refractions, shadows, more light sources, and more objects. Ultimately, in the end I hope to be able to create a somewhat realistic rendering of the famous [Cornell Box Image](http://graphics.ucsd.edu/~henrik/images/imgs/cboxgi.jpg).

I also hope to add some way to watch live as the imaged is ray traced (I'm told this is an experience).

# Running

Running `./gradlew run` from the command line will create the image file `image.ppm`, which is the rendered image in the [netpbm](https://en.wikipedia.org/wiki/Netpbm_format) image format  

# Examples

Without Specular Term
![](http://oi68.tinypic.com/6ny14j.jpg)

With Specular Term
![](http://i.imgur.com/fetKoeJ.png)

# License
[Unlicense](http://unlicense.org/)
