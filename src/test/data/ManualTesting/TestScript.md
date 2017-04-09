## TaskBoss Test Script

This is a test script for TaskBoss that contains sample commands for all available operations in TaskBoss. Please note that it is written based on the assumption that a tester will run every command in the order listed below with our provided sample data.

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
1. Help
<pre>
help
h
Expected behavior: TaskBoss will pop up a help window of UserGuide.
</pre>

2. Add floating tasks
<pre>
add return $10 to Jack!
Expected behavior: Add a task with name "return $10 to Jack!".

add contact Simon via simondd@email.com~
Expected behavior: Add a task with name "contact Simon via simondd@email.com~".

a return $13 to Jack!
Expected behavior: Add a task with name "return $13 to Jack!".
</pre>

3. Add events tasks
<pre>
add IT fair sd/19 April 2017 ed/last mon of apr
Expected behavior: Add a task with name "IT fair", start date "Apr 19, 2017" and end date "Apr 24, 2017".
</pre>

4. Add deadline tasks
<pre>
add project milestone ed/27 mar
Expected behavior: Add a task with name "project milestone" and end date "Mar 27, 2017".
</pre>

5. Edit a task
<pre>
edit 9 c/Project p/no
Expected behavior: Edit the task with index 9; change it to Project category, with no priority.

e 10 c/Project p/no
Expected behavior: Edit the task with index 10; change it to Project category, with no priority.

edit 10 ed/
Expected behavior: Edit the task with index 10; Remove its end date.
</pre>

6. Delete task(s)
<pre>
delete 23
Expected behavior: Delete the task with index 23.

delete 22 24 25 26
Expected behavior: Delete the tasks with index 22, 24, 25, 26.

d 1
Expected behavior: Delete the task with index 1.
</pre>

7. Add recurring task (none [default], daily, weekly, monthly, yearly)
<pre>
add buy gift for son r/weekly
Expected behavior: Add a task with name "add buy gift for son" and recurs weekly.
</pre>

8. Mark task(s) as done
<pre>
mark 1
Expected behavior: Mark the task with index 1 as done. The task will only appear in Done category. 

mark 2 5 6
Expected behavior: Mark the tasks with index 2, 5, 6 as done. The tasks will only appear in Done category. 

m 5
Expected behavior: Mark the task with index 5 as done. The task will only appear in Done category. 
</pre>

9. Mark recurring task(s) as done
<pre>
mark 2
Expected behavior: Mark the recurring task with index 2 as done. 
                   The task's start date and end date will be updated according to its recurring type.

mark 13
Expected behavior: Mark the recurring task with index 13 as done. 
                   The task's start date and end date will be updated according to its recurring type.
</pre>

10. Terminate recurring task(s)
<pre>
terminate 13 14
Expected behavior: Terminate the recurring tasks with index 13 and 14. 
                   The tasks will only appear in Done category.

t 22
Expected behavior: Terminate the recurring task with index 22. 
                   The task will only appear in Done category.
</pre>

11. Find task by keywords
<pre>
find pay
Expected behavior: Find all the tasks whose name and information contains the keyword "pay".

f pay
Expected behavior: Find all the tasks whose name and information contains the keyword "pay".
</pre>

12. Find task by date (natural language or single integer: year/date)
<pre>
find sd/january
Expected behavior: Find all the tasks whose start date contains January.

find ed/28
Expected behavior: Find all the tasks whose end date contains 28.
</pre>

13. List (list all tasks or list tasks in a category)
<pre>
list
Expected behavior: List all the unfinished tasks.

list c/Events
Expected behavior: List all the unfinished tasks under the category Events.

l c/done
Expected behavior: List all the finished tasks
</pre>

14. Unmark done task(s)
<pre>
unmark 2 3
Expected behavior: Unmark the tasks with index 2 and 3.
                  The tasks will be removed from Done category and added back to the unfinished task list.

um 1
Expected behavior: Unmark the task with index 1.
                   The task will be removed from Done category and added back to the unfinished task list.
</pre>

15. Rename a category
<pre>
name personal life
Expected behavior: The category Personal will be renamed as Life.

n work company
Expected behavior: The category Work will be renamed as Company.
</pre>

16. Sort tasks by priority, start date or end date
<pre>
sort p
Expected behavior: Sort all the tasks by their priorities. 
                   Task with the high priority will appear at the top of the panel.

sort sd
Expected behavior: Sort all the tasks by their start dates.
                   Task with the earlier start date will appear at the top of the panel.

s ed
Expected behavior: Sort all the tasks by their end dates.
                   Task with the earlier end date will appear at the top of the panel.
</pre>

17. Clear all tasks / clear a category
<pre>
c c/Meetings
Expected behavior: Clear all the tasks under Meetings Category.
                   The Meetings category will be removed from the category panel.

clear
Expected behavior: Clear all the tasks in TaskBoss.
                   The task list panel and category list panel will be blank.
</pre>

18. Undo
<pre>
undo
Expected behavior: Undo the previous operation: clear.

u
Expected behavior: Undo the previous operation: c c/Meetings.
</pre>

19. Redo
<pre>
redo
Expected behavior: Redo the previous undo operation: c c/Meetings.

r
Expected behavior: Redo the previous undo operation: clear.
</pre>

20. Save TaskBoss data to a specified filepath
<pre>
save C://Users/Desktop/TaskBoss    // Windows
Expected behavior: Create the TaskBoss folder in desktop and save the taskboss.xml file in it.

sv /Users/<Username>/Desktop/MyTasks         // Mac
Expected behavior: Create the MyTasks folder in desktop and save the taskboss.xml file in it.
</pre>

21. Exit TaskBoss
<pre>
exit
x
Expected behavior: Exit TaskBoss.
</pre>

<h4 align="center">- End of document -</h4>
