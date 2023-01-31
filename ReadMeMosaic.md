### Statement:
We were able to successfully implement Mosaicking via a scripting command and the GUI.

### Our Implementation:
To choose the seeds, we wrote a strategy that randomly selected a provided number of seeds from the
image. This was the baseline strategy, but we thought in the future others might want to use a
better strategy to ensure seeds were not clustered. As such, in our Mosaicking function, we treated
the ChooseSeeds strategy as a Function object, so future implementers can replace it at will.

Then, to classify pixels into seeds, we iterated over all the seeds and calculated the distance
from that pixel to each seed. We then chose the seed with the minimum distance.

### Our Implementation With Their Design:
Their design made use of the command design pattern in order to delegate all the functionality from
the controller. So, when we implemented Mosaic, we created a new Function object that contained all
the functionality of Mosaic and added an instance of it to their command map.

It is worth noting that we directly edited their command map instead of making a new controller via
extension. This was because they had five different controllers, all of which are tightly coupled.
Their controller that handled all the features, ControllerFeatures, was referenced or extended in
all the other controllers. So, if we extended ControllerFeatures to add the new functionality,
we also would have needed to make new versions of all the other controllers while only changing the
features object to our new ControllerFeatures instead of the old one. We decided this was
unnecessarily complicating things so, after a talk with Vidoje and some TA's, we decided to directly
edited their command map.

To add the Mosaicking functionality to the view, we added a new Mosaic button and a new counter for
the number of seeds. We did this in the constructor of their view, which is where they did all of
their Swing object declarations. To handle the functionality of the button, we added a clause to
their requestAction() method, which was for event handling. It was done in a very similar manner to
how they did brighten. We got the value of the seeds counter and then made a call to our
ControllerFeatures with a new Mosaicking command.