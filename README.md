[![Codacy Badge](https://app.codacy.com/project/badge/Grade/2bf3f99fc1aa4d33b472f3660a88b0c9)](https://www.codacy.com/gh/CDonalO/Scrap-Kitchen-COSC345/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=CDonalO/Scrap-Kitchen-COSC345&amp;utm_campaign=Badge_Grade) [![codecov](https://codecov.io/gh/CDonalO/Scrap-Kitchen-COSC345/branch/main/graph/badge.svg?token=7B8DTHOTGY)](https://codecov.io/gh/CDonalO/Scrap-Kitchen-COSC345)

#Scrap Kitchen

After completing the beta user testing, this feedback was generally very positive but we where given a few ideas on what we could change:

- One major feedback was that is was hard to find out where the button was to actually add the 
  recipes to the meal planner and your shopping list, the three dotted add button was the wrong design choice

We have changed this to a option box in the bottom right corner which is easy to see and once opened the two option expand to 
allow you to ether add a recipe to your weekly meal planner or add to shopping list. 

- We where asked to implement some way of viewing all the recipes from the main screen, the current way to do
  so is by selecting Antarctica and view the recipes from there. 

Researching this we find this quit hard to figure out so out solution was to add a description on how to navigate the app
main screen, with this we describe how by clicking on the Antarctica continent you can access all the recipes. 

There where a few design feature's that where recommended to update: 
- rounding off the button corners to make it look more appealing 

In the shopping list feature we have rounded off all shopping list items that you add to the list as well as changing the colour 
of the selected item when ticking it off.

There where a few suggestion for the app that unfortunately where just to much for as to be able to expand on since most of
them involved updating the structure of the database which we decided as a group we wouldn't touch once the beta was released so we
wouldn't add any more bugs into the software. These changes would have also required us to change our database builder script
which could have introduced further issues. If we had more time adding these quality of life features to the app would likely be our
focus.
Things like adding photos for each recipe instead of the country emoji would of been a nice touch and adding nutritional 
information into every recipe or a vegetarian mode. 

The last major change was implementing the MVVM design pattern which was a major refactor project ongoing since before the Beta to
enable easier unit testing and to make the code more readable and easier to understand. While our code coverage is not as high as
we would like it as it hasn't been a priority since the beta, our current app design would make getting this higher very simple.

with respect to the copyright of all the recipes, there are many source that say the copy right of recipes are only so when
the recipe itself is completely original eg "from my grandma" but the idea itself can not be. All these recipes have been 
slightly altered from the various websites they where gained from including some methods so in fact can even be seen as original.

App has been tested on a google pixel 5 using gesture navigation functional in both night mode and day mode

