# Project 4 (Name in progress)!
###### An Endless Runner Game
###### CS 441 Program 04 @ Binghamton University

----

## How to play

Tap on the screen to jump. Avoid dangerous vehicles as you rush into oncoming traffic!

----

## Features

 - Original pixel art drawn in Aseprite
 - Parallax skyline

----

## TODO

 - Player art and running animations
 - More art can be done
 - Optimize some more latency issues
 - Draw and add more vehicles

----

## Commits

    commit c73d1db75323aba4f7df45e0c45c8ba4cb3473d3
    Author: Tim Hung <thung1@binghamton.edu>
    Date:   Mon Mar 6 22:00:38 2017 -0500
    
        Import vehicle art
    
    commit deba300b1376de40f76186723a79da417156debc
    Author: Tim Hung <thung1@binghamton.edu>
    Date:   Mon Mar 6 21:30:11 2017 -0500
    
        Add lost text and update game states
    
    commit 9a09a26c422dae98100da7a4a04f544ea5d5e5b5
    Author: Tim Hung <thung1@binghamton.edu>
    Date:   Mon Mar 6 18:40:40 2017 -0500
    
        Implement vehicle hit collisions and game restart on touch
    
    commit 409c663d7c8036006c6107520f3b76c42defe2e3
    Author: Tim Hung <thung1@binghamton.edu>
    Date:   Mon Mar 6 17:33:11 2017 -0500
    
        Create Vehicle class
    
    commit 8beb9b06fd9b6dd02bf7fc8946275d7bd1ad37ac
    Author: Tim Hung <thung1@binghamton.edu>
    Date:   Mon Mar 6 17:12:12 2017 -0500
    
        Implement various attempts to  improve latency
    
    commit 98a42bdb980845757fcb92b6c5dd99be254ca3a5
    Author: Tim Hung <thung1@binghamton.edu>
    Date:   Mon Mar 6 16:49:22 2017 -0500
    
        Implement ScrollableBackground and add images
        
        ScrollableBackground is designed to deal with scrolling backgrounds to
        simulate the effect of parallax. As of now, 4 different layers of
        parallax are being drawn, however, the game runs extremely slowly.
        Played around with the Memory Allocation Tracker, but wasn't able to
        find anything. Speeding this up is a number 1 priority.
        
        Also, my pixel art that I drew using Aseprite was added.
    
    commit 010a5b23520a33109c4590a6395c65629044799d
    Author: Tim Hung <thung1@binghamton.edu>
    Date:   Sun Mar 5 22:17:01 2017 -0500
    
        Draw and add highway with collisions
    
    commit 43dafcdb89ad7f874aa0bd471144ab296f17c30a
    Author: Tim Hung <thung1@binghamton.edu>
    Date:   Sun Mar 5 16:37:21 2017 -0500
    
        Implement jump
    
    commit e03955e496c4f0b2f52308600df672134f6a442f
    Author: Tim Hung <thung1@binghamton.edu>
    Date:   Sun Mar 5 16:24:20 2017 -0500
    
        Create player and force landscape
    
    commit ec5ebfd1f81789a2acfe827ba9e83596527259dc
    Author: Tim Hung <thung1@binghamton.edu>
    Date:   Sun Mar 5 01:06:23 2017 -0500
    
        Create .gitignore
    
    commit 06e18cc0bfd22d01bada927869bbd8d3101a92ca
    Author: Tim Hung <thung1@binghamton.edu>
    Date:   Sun Mar 5 01:04:30 2017 -0500
    
        Create skeleton code from prev project
    
    commit 865d35f376ca70dd2f7d2d6d1c7f8ca528d75c32
    Author: Tim Hung <thung1@binghamton.edu>
    Date:   Sat Mar 4 23:45:23 2017 -0500
    
        Create README
