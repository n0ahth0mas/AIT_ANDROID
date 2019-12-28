# AIT ANDROID
Android Projects I worked on while at the Aquincum Instutite of Technology in Budapest in my mobile software development course.

## Table of Contents  
[Project 1: Minesweeper](#minesweeper)  
[Project 2: Shopping List](#shopping)  
[Final Project: HomeCook](https://github.com/n0ahth0mas/AndroidFinal) 
[Additional Information](#extra) 

<a name="minesweeper">

# PROJECT 1: MINESWEEPER

The goal of this first project was to create a basic implementation of Minesweeper using our introductory knowledge in Android Studio and Kotlin. The main specifications of this project were:
 1. learn the basics of Kotlin programming
 2. create Activities
 3. implement custom XML UI
 4. draw on custom views
 5. apply basic View components

 I was able to extend these specification to further build on my understading of the Android platform - in this project, I added additional images to the game interface and created a color changing gradient background as well.

| Blank Minefield  | In-progress Game | Winning Screen | 
| ------------- | ------------- |------------- |
| ![Blank Minefield](/images/ms1.png) | ![In-progress Game](/images/ms2.png)  | ![Winning Screen](/images/ms3.png)  |

<a name="shopping">

# PROJECT 2: SHOPPING LIST

The goal of this second project was to create a more advanced Android application to further our understanding and to give us inspiration for future personal projects by creating a shopping list that stores user input data. The specifications of this project were to:
 1. create multiple activities
 2. use RecyclerView to display lists
 3. implement custom dialogs
 4. create splash animations
 5. introduct persistant data storage through the Room library

 This project allowed me to expand my understanding on data storage and SQL queries, which will help for future projets using Firebase and other APIs. This has also introduced me to animations, something which I am excited to utilize in other assignments and personal projects.

| Shopple Splash Screen  | Empty Shopping List | Item Creation Screen |  Filled Shopping List | 
| ------------- | ------------- |------------- |------------- |
| ![Shopple Splash](/images/slSplash.png) | ![Empty Shopping List](/images/slBlank.png)  | ![Creation Screen](/images/slItem.png)  | ![Filled List](/images/slList.png)  |

# FINAL PROJECT: HOMECOOK

This was the final project for my mobile course. There were no real instructions for this project other than expand upon all other lessons, so my project partner and I did exactly that. Our final product was Homecook (located [here](https://github.com/n0ahth0mas/AndroidFinal)), a cooking app where users sign in and can store the ingredients they currently have, search recipes based on main ingredient, and view their favorited recipes. The main elements of this project were:
1. Google Sign-on
2. Firebase storage of userid, favorite recipes (by recipe id), and ingredient
3. API call and manipulation using Repos ([The MealDB](https://themealdb.com/))
4. Implementation of other online libraries
5. Fragment backstack management

| Homecook Splash Screen  | Favorited Recipes | Recipe Details | 
| ------------- | ------------- |------------- |
| ![Homecook Splash Screen](/images/homecook_splash.png) | ![Favorited Recipes](/images/homecook_favs.png)  | ![Recipe Details](/images/homecook_details.png)  |

| Ingredients  | Ingredient Dialog | Search Results | 
| ------------- | ------------- |------------- |
| ![Ingredeints](/images/homecook_ingredients.png) | ![Ingredient Dialog](/images/homecook_ingredientdialog.png)  | ![Search Results](/images/homecook_search.png)  |

<a name="extra">

# ADDITIONAL INFORMATION

## Built With:
* [Android Studio](https://developer.android.com/studio) - Development environment used to create all applications
* [Kotlin](https://kotlinlang.org/) - Programming language used for development
* [The MealDB](https://themealdb.com/) - Database used for HomeCook
* [Firebase](https://firebase.google.com/) - Used for cloud-based storage
