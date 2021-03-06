## TaskBoss Test Script

This is a test script for TaskBoss that contains sample commands for all available operations in TaskBoss. Please note that it is written based on the assumption that a tester will run the commands in the order listed below with our provided sample data.

#### Pre-requisites:
<pre>
> Java version 1.8.0_60
> The latest (v0.5) TaskBoss.jar
</pre>

#### Setup:
1. Run TaskBoss.jar.
2. Locate `/data/taskboss.xml` that has been created in the directory containing TaskBoss.jar.
3. Replace contents of this file with our sample data found [here](https://github.com/CS2103JAN2017-W14-B2/main/blob/95b001c5876d2cab836a6d8e0aebd230cab5ba46/src/test/data/ManualTesting/SampleData.xml).

#### Commands:
**1. Help**
<pre>
help
h
</pre>
Expected behavior: TaskBoss will pop up a help window of UserGuide.

**2. Add floating tasks**
<pre>
add return $10 to Jack!
</pre>
Expected behavior: Add a task with name "return $10 to Jack!".
<pre>
add contact Simon via simondd@email.com~
</pre>
Expected behavior: Add a task with name "contact Simon via simondd@email.com~".
<pre>
a return $13 to Jack!
</pre>
Expected behavior: Add a task with name "return $13 to Jack!".

**3. Add events tasks**
<pre>
add IT fair sd/19 April 2017 ed/last mon of apr
</pre>
Expected behavior: Add a task with name "IT fair", start date "Apr 19, 2017" and end date "Apr 24, 2017".

**4. Add deadline tasks**
<pre>
add project milestone ed/27 mar
</pre>
Expected behavior: Add a task with name "project milestone" and end date "Mar 27, 2017".

**5. Edit a task**
<pre>
edit 9 c/Project p/no
</pre>
Expected behavior: Edit the task with index 9; change it to Project category, with no priority.
<pre>
e 10 c/Project p/no
</pre>
Expected behavior: Edit the task with index 10; change it to Project category, with no priority.
<pre>
edit 10 ed/
</pre>
Expected behavior: Edit the task with index 10; Remove its end date.

**6. Delete task(s)**
<pre>
delete 23
</pre>
Expected behavior: Delete the task with index 23.
<pre>
delete 22 24 25 26
</pre>
Expected behavior: Delete the tasks with index 22, 24, 25, 26.
<pre>
d 1
</pre>
Expected behavior: Delete the task with index 1.

**7. Add recurring task (none [default], daily, weekly, monthly, yearly)**
<pre>
add buy gift for son r/weekly
</pre>
Expected behavior: Add a task with name "add buy gift for son" and recurs weekly.

**8. Mark task(s) as done**
<pre>
mark 1
</pre>
Expected behavior: Mark the task with index 1 as done. The task will only appear in Done category. 
<pre>
mark 2 5 6
</pre>
Expected behavior: Mark the tasks with index 2, 5, 6 as done. The tasks will only appear in Done category. 
<pre>
m 5
</pre>
Expected behavior: Mark the task with index 5 as done. The task will only appear in Done category. 

**9. Mark recurring task(s) as done**
<pre>
mark 2
</pre>
Expected behavior: Mark the recurring task with index 2 as done. 
                   The task's start date and end date will be updated according to its recurring type.
<pre>
mark 13
</pre>
Expected behavior: Mark the recurring task with index 13 as done. 
                   The task's start date and end date will be updated according to its recurring type.

**10. Terminate recurring task(s)**
<pre>
terminate 13 14
</pre>
Expected behavior: Terminate the recurring tasks with index 13 and 14. The tasks will only appear in Done category.
<pre>
t 22
</pre>
Expected behavior: Terminate the recurring task with index 22. The task will only appear in Done category.

**11. Find task by keywords**
<pre>
find pay
</pre>
Expected behavior: Find all the tasks whose name and information contains the keyword "pay".
<pre>
f pay
</pre>
Expected behavior: Find all the tasks whose name and information contains the keyword "pay".

**12. Find task by date (natural language or single integer: year/date)**
<pre>
find sd/january
</pre>
Expected behavior: Find all the tasks whose start date contains January.
<pre>
find ed/28
</pre>
Expected behavior: Find all the tasks whose end date contains 28.

**13. List (list all tasks or list tasks in a category)**
<pre>
list
</pre>
Expected behavior: List all the unfinished tasks.
<pre>
list c/Events
</pre>
Expected behavior: List all the unfinished tasks under the category Events.
<pre>
l c/done
</pre>
Expected behavior: List all the finished tasks

**14. Unmark done task(s)**
<pre>
unmark 2 3
</pre>
Expected behavior: Unmark the tasks with index 2 and 3.
                  The tasks will be removed from Done category and added back to the unfinished task list.
<pre>
um 1
</pre>
Expected behavior: Unmark the task with index 1.
                   The task will be removed from Done category and added back to the unfinished task list.

**15. Rename a category**
<pre>
name personal life
</pre>
Expected behavior: The category Personal will be renamed as Life.
<pre>
n work company
</pre>
Expected behavior: The category Work will be renamed as Company.

**16. Sort tasks by priority, start date or end date**
<pre>
sort p
</pre>
Expected behavior: Sort all the tasks by their priorities. 
                   Task with the high priority will appear at the top of the panel.
<pre>
sort sd
</pre>
Expected behavior: Sort all the tasks by their start dates.
                   Task with the earlier start date will appear at the top of the panel.
<pre>
s ed
</pre>
Expected behavior: Sort all the tasks by their end dates.
                   Task with the earlier end date will appear at the top of the panel.

**17. Clear all tasks / clear a category**
<pre>
c c/Meetings
</pre>
Expected behavior: Clear all the tasks under Meetings Category.
                   The Meetings category will be removed from the category panel.
<pre>
clear
</pre>
Expected behavior: Clear all the tasks in TaskBoss.
                   The task list panel and category list panel will be blank.

**18. Undo**
<pre>
undo
</pre>
Expected behavior: Undo the previous operation: clear.
<pre>
u
</pre>
Expected behavior: Undo the previous operation: c c/Meetings.

**19. Redo**
<pre>
redo
</pre>
Expected behavior: Redo the previous undo operation: c c/Meetings.
<pre>
r
</pre>
Expected behavior: Redo the previous undo operation: clear.

**20. Save TaskBoss data to a specified filepath (OS-dependent, filepath may differ under different environment)**
<pre>
save C://Users/Desktop/TaskBoss    // Windows
</pre>
Expected behavior: Create the TaskBoss folder in desktop and save the taskboss.xml file in it.
<pre>
sv /Users/<Username>/Desktop/MyTasks         // Mac
</pre>
Expected behavior: Create the MyTasks folder in desktop and save the taskboss.xml file in it.

**21. Exit TaskBoss**
<pre>
exit
x
</pre>
Expected behavior: Exit TaskBoss.

<h4 align="center">- End of document -</h4>
