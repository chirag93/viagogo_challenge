## viagogo_challenge
# 1. Running the  <br />
    run : `sh event_finder` or `./event_finder` in the root folder of this repository <br />
     Input can be in decimal format for Example: `10.5,8.5`
    <br />
# 2. config.properties file and assumptions.<br />
   **Below are the fields mentioned at the config.properties file**<br />
    `MAX_NO_TICKETS=50`  #Assuming that there are 50 tickets for event<br />
    `MIN_NO_TICKETS=0`    #minimum number of tickets for a event can be 0<br />
    `MAX_TICKET_PRICE=1000` #Assuming maximum amout for a ticket to be 1000 USD<br />
    `MIN_TICKET_PRICE=50` #Assuming minimum amout for a ticket to be 0 USD<br />
    `MIN_X_RANGE=-10`     #X coordinate maximum negative range<br />
    `MAX_X_RANGE=10`      #X coordinate maximum positive range<br />
    `MAX_Y_RANGE=10`     #Y coordinate maximum positive range<br />
    `MIN_Y_RANGE=-10`     #Y coordinate maximum negative range<br />
    `MAX_LOCATIONS=500`   #Assuming total locations in the world to be 500<br />
    `MAX_EVENTS_TO_DISPLAY=5` #Maximim number of events to display, this can be configured<br />
    
    
# 3. How to Handle if there were multiple events in a single location ? 
    
    Currently every location has a event which is a map which makes it easy to change to multiple events<br/ > 
    Example:- 
   `Map<Location,Event> data ...` con be changed to `Map<Location,events[]>` which will have a list of events for every           location.
   And the eventGenerator will return a array of events. Eg: `public List<Event> generateEvents(){...}`

# 4. How would I change your program if I was working with a much larger world size?

    The locaiton data and the events would be stored assuming we will be storing this data in a database. While dealing with  large data one of the significant costly operations would be to retrive a perticular data. So it would be stored in the chunks of different events as pertheir location, so that while retrieving the data we don't have to search in the big dataset, we just have to search in the small chunk of data where its relevant.
