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
</pre>

2. Add floating tasks
<pre>
add return $10 to Jack!
add contact Simon via simondd@email.com~
a return $13 to Jack!
</pre>

3. Add events tasks
<pre>
add IT fair sd/19 April 2017 ed/last mon of apr
</pre>

4. Add deadline tasks
<pre>
 add project milestone ed/27 mar
</pre>

5. Edit a task
<pre>
edit 9 c/Project p/no
e 10 c/Project p/no
edit 10 ed/
</pre>

6. Delete task(s)
<pre>
delete 23
delete 22 24 25 26
d 1
</pre>

7. Add recurring task (none [default], daily, weekly, monthly, yearly)
<pre>
add buy gift for son r/weekly
</pre>

8. Mark task(s) as done
<pre>
mark 1
mark 2 5 6
m 5
</pre>

9. Mark recurring task(s) as done
<pre>
mark 2
mark 13
</pre>

10. Terminate recurring task(s)
<pre>
terminate 13 14
t 22
</pre>

11. Find task by keywords
<pre>
find pay
f pay
</pre>

12. Find task by date (natural language or single integer: year/date)
<pre>
find sd/january
find ed/28
</pre>

13. List (list all tasks or list tasks in a category)
<pre>
list
list c/Events
l c/done
</pre>

14. Unmark done task(s)
<pre>
unmark 2 3
um 1
</pre>

15. Rename a category
<pre>
name personal life
n work company
</pre>

16. Sort tasks by priority, start date or end date
<pre>
sort p
sort sd
s ed
</pre>

17. Clear all tasks / clear a category
<pre>
c c/Meetings
clear
</pre>

18. Undo
<pre>
undo
u
</pre>

19. Redo
<pre>
redo
r
</pre>

20. Save TaskBoss data to a specified filepath
<pre>
save C://Users/Desktop/TaskBoss    // Windows
sv /Users/<Username>/Desktop/MyTasks         // Mac
</pre>

21. Exit TaskBoss
<pre>
exit
x
</pre>

<h4 align="center">- End of document -</h4>
