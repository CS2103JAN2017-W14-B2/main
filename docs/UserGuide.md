# TaskBoss - User Guide

By : `Team W14-B2`  &nbsp;&nbsp;&nbsp;&nbsp; Since: `Mar 2017`  &nbsp;&nbsp;&nbsp;&nbsp; Licence: `MIT`

---

1. [Introduction](#1-introduction)
2. [Quick Start](#2-quick-start)
3. [Features](#3-features)<ed>
   > * 3.1 [Viewing help](#31-viewing-help--help--h)<ed>
   > * 3.2 [Adding a task](#32-adding-a-task--add--a--plus-sign)<ed>
   > * 3.3 [Listing all tasks](#33-listing-all-tasks--list--l)<ed>
   > * 3.4 [Editing a task](#34-editing-a-task--edit--e)<ed>
   > * 3.5 [Finding task(s) by Keywords or by Datetime](#35-finding-tasks-by-keywords-or-by-datetime--find--f)<ed>
   > * 3.6 [Deleting task(s)](#36-deleting-tasks--delete--d--minus-sign)<ed>
   > * 3.7 [Clearing tasks by category](#37-clearing-tasks-by-category--clear--c)<ed>
   > * 3.8 [Viewing a task](#38-viewing-a-task--view--v)<ed>
   > * 3.9 [Renaming a category](#39-renaming-a-category--name--n)<ed>
   > * 3.10 [Marking task(s) done](#310-marking-tasks-done--mark--m)<ed> 
   > * 3.11 [Terminating task(s)](#311-terminating-tasks--terminate--t)<ed> 
   > * 3.12 [Unmarking task(s)](#312-unmarking-tasks--unmark--um)<ed> 
   > * 3.13 [Undoing a command](#313-undoing-a-command--undo--u)<ed>
   > * 3.14 [Redoing a command](#314-redoing-a-command--redo--r)<ed> 
   > * 3.15 [Sorting tasks](#315-sorting-tasks--sort--s)<ed>
   > * 3.16 [Saving and exporting the data](#316-exporting-the-data--save--sv)<ed>
   > * 3.17 [Exiting the program](#317-exiting-the-program--exit--x)<ed>
4. [FAQ](#4-faq)
5. [Command Summary](#5-command-summary)
 

## TaskBoss Prototype
<img src="images/TaskBoss (all tasks).png" width="600"> <br>
An image of TaskBoss

## 1. Introduction
Have you ever been overwhelmed with too many tasks? Perhaps, a couple of these tasks might have even slipped your mind? Well, TaskBoss is here to save the day! TaskBoss is a user-friendly task manager that supports a wide range of features. Become the boss of your tasks and start using TaskBoss today!

## 2. Quick Start
1. Ensure you have Java version `1.8.0_60` or later installed in your Computer.<br>
   Download the latest Java version if you are unable to run TaskBoss.<br>
   > Having any Java 8 version is not enough. <br>
   > This app will not work with earlier versions of Java 8.

2. Install the latest `TaskBoss.jar` from the [releases](../../../releases) tab.
3. Copy the file to the folder you want to use as the home folder for TaskBoss.
4. Double-click the file to start the app. The GUI should appear within a few seconds.
5. Type a command in the command box and press <kbd>Enter</kbd> to execute it. <br>
   e.g. typing **`help`** and pressing <kbd>Enter</kbd> will open the help window.
   Here are some commands you can try:
   * **`list`** : lists all tasks
   * **`add`**: `submit proposal p/high i/group project ed/tomorrow c/project`
     adds a task named `submit proposal` to your list of tasks
   * **`delete`**` 3` : deletes the third task shown in the current list
   * **`exit`** : exits the app <br>


## 3. Features

> **Command Format**
>
> * Words in `UPPER_CASE` are the parameters.
> * Items in `SQUARE_BRACKETS` are optional.
> * Items with `...` after them can have multiple instances.
> * Parameters can be in any order.

<br>

> **List of Commands**

|Command    |Description                                                          |
|:---------:|:-------------------------------------------------------------------:|
|[**help / h**](#31-viewing-help--help--h)                       |View help window             |
|[**add / a / +**](#32-adding-a-task--add--a--plus-sign)                         |Add a task             |
|[**list / l**](#33-listing-all-tasks--list--l)                  |List the tasks         |
|[**edit / e**](#34-editing-a-task--edit--e)                     |Edit a task            |
|[**find / f**](#35-finding-tasks-by-keywords-or-by-datetime--find--f)           |Find tasks by keywords or by datetime             |
|[**delete / d / -**](#36-deleting-tasks--delete--d--minus-sign)                |Delete a task          |
|[**clear / c**](#37-clearing-tasks-by-category--clear--c)       |Clear tasks in category|
|[**view / v**](#38-viewing-a-task--view--v)                    |View a task            |
|[**name / n**](#39-renaming-a-category--name--n)         |Rename a category      |
|[**mark / m**](#310-marking-tasks-done--mark--m)               |Mark a task as done and updates task's dates    |
|[**terminate / t**](#311-terminating-tasks--terminate--t)          |Terminate a recurring task    |
|[**unmark / um**](#312-unmarking-tasks--unmark--um)       |Unmark previously marked or terminated tasks and updates task's dates | 
|[**undo / u**](#313-undoing-a-command--undo--u)                 |Undo a task            |
|[**redo / r**](#314-redoing-a-command--redo--r)                 |Redo a task            |
|[**sort / s**](#315-sorting-tasks--sort--s)                     |Sort tasks by deadline or by priority|
|[**save / sv**](#316-exporting-the-data--save--sv)                |Save TaskBoss          |
|[**exit / x**](#317-exiting-the-program--exit--x)               |Exit TaskBoss          |

<br>

### 3.1. Viewing help : `help / h`

Displays a window with a summary of all the different commands and features available and guidelines on how to use them.
Format: `help`

### 3.2. Adding a task : `add / a / plus sign`

Adds a task<br>
Format: `add TASK_NAME [i/INFO] [sd/START_DATE] [ed/END_DATE] [c/CATEGORY] [p/PRIORITY_LEVEL] [r/RECURRENCE]`

> * Date can be written in UK-time format with slashes, `i.e dd-mm-yyyy` or natural language, `i.e this sunday`.  <br>
> * Time should be in 24-hour clock format, i.e `1830`, or 12-hour format with AM or PM (case-insensitive) next to it, i.e `6:30 PM`. <br>
> * Priority level is `no` by default, and can be either `yes` or `no` OR `y` or `n` (case-insensitive).
> * Category will be configured to be in lower case form with the the first letter in upper case. `i.e work will become Work`
> * The task will have the `Alltasks` category by default.
> * Recurrence is `none` by default, and can be either `daily`, `weekly`, `monthly`, `yearly` or `none` (case-insensitive).
> * Order of optional parameters does not matter. <br>
  - paramters include the task's name, information, start date, end date, categories, priority, and recurrence.
> * All paramters are optional except TASK_NAME
> * All prefexes are case-sensitive <br>
  - prefexes include i/, sd/, ed/, c/, p/, and r/.

Examples:

* `add Email progress report ed/19-02-2017 c/Project p/YES r/weekly`
* `add Dinner with Jim i/In Orchard road sd/next friday ed/19-02-2017 c/Meeting p/no`
* `add Contract signing sd/tomorrow at 3 PM c/Work i/company merger`


### 3.3. Listing all tasks : `list / l`

Shows a list of all tasks<br>
Format: `list` 

Shows a list of tasks under a specified category <br>
Format: `list c/CATEGORY NAME` 

Example:
* `list`
* `list c/Project`<br>


### 3.4. Editing a task : `edit / e`

Edits an existing task<br>
Format: `edit INDEX [TASK NAME] [i/INFO] [sd/START_DATE] [ed/END_DATE] [c/CATEGORY] [p/PRIORITY_LEVEL] [r/RECURRENCE]`

> * Edits the task at the specified `INDEX`.
    The index refers to the index number last shown in the last task listing.<br>
    The index **must be a positive integer** (*e.g. 1, 2, 3, ...*).
> * At least one of the optional paramaters must be provided.
> * Existing task details will be updated to the new input values.
> * Order of the optional parameters does not matter.

Examples:

* `edit 1 i/Use Stack ed/23:59`<br>
  Edits the task information and end time of the first task to be `Use Stack` and `23:59` respectively.
  
* `edit 3 p/yes`<br>
  Edits the priority level of third task to yes. 

### 3.5. Finding task(s) by Keywords or by Datetime : `find / f`

Finds tasks whose names or information contain any of the given keywords<br>
Format: `find KEYWORDS` 

Finds tasks whose start datetime matches the given datetime<br>
Formats: `find sd/date and time` `find ed/date and time`

> * The search for name is case-insensitive. e.g `Project` will match `project`.
> * The order of the named keywords does not matter. e.g. `meeting project` will match `project meeting`.
> * Only full words will be matched when searching by keywords. e.g. `meeting` will match `meetings`.
> * A specific date can be searched in the UK-time format with slashes, `i.e dd-mm-yyyy` or natural language which also supports searching for a specific month, `i.e december`. 
> * Any numeric day of month or year can also be searched, `i.e 2017`.

Examples:

* `find Meeting`<br>
  Returns all tasks whose name or information contains the word `Meeting`.
  
* `find airport CEO`<br>
  Returns all tasks whose name or information contains at least one of the keywords: `airport` and `CEO`.

* `find sd/2 april`<br>
  Returns all tasks with start date on 2nd April of the current year.
  
* `find ed/february`<br>
  Returns all tasks with end date in February.
  
### 3.6. Deleting task(s) : `delete / d / minus sign`

Deletes the specified task<br>
Format: `delete INDEX...`

> * Deletes the task(s) at the specified `INDEX`. <br>
> * The index numbers refers to the index number last shown in the last task listing. <br>
> * The index numbers **must be a positive integer** (*e.g. 1, 2, 3, ...*).
> * Multiple indexes are allowed. All tasks at the specified indexes will be deleted.

Examples:

* `list`<br>
  `delete 2`<br>
  Deletes the second task.
  
* `delete 1 2`<br>
  Deletes the first and second task.
  
* `find meeting`<br>
  `delete 1`<br>
  Deletes the first task in the results of the `find` command.

### 3.7. Clearing tasks by category : `clear / c`

Clears all tasks under the specified category<br>
Format: `clear c/CATEGORY`

Clears all tasks<br>
Format: `clear`

> * Category names are case-insensitive. <br>
> * The build-in categories are `Alltasks` and `Done`. <br>
> * Clearing `Alltasks` will result in the clearance of all the tasks except for those under the `Done` catgeory. <br>
> * Clearing `Done` will result in the clearance of all the tasks under `Done`. <br>

Examples:

* `clear c/work`
 clears all tasks with the catgeory `work`
 
 * `clear`
 clears all tasks under all categories

### 3.8. Viewing a task : `view / v`

Highlights the specified task<br>
Format: `view INDEX`

> * Highlights the task at the specified `INDEX`. <br>
> * The index refers to the index number last shown in the last task listing.<br>
> * The index **must be a positive integer** (*e.g. 1, 2, 3, ...*).

Examples:

* `view 1`
 Highlights the first task

### 3.9. Renaming a category : `name / n`

Renames a category <br>
Format: `name EXISTING_CATEGORY NEW_CATEGORY`

> * Renames all the `EXISTING_CATEGORY` categories to `NEW_CATEGORY`. <br>
> * Built-in categories, `Alltasks` and `Done`, cannot be renamed.
> * Existing cannot be renamed to Built-in categories

Example:

* `name work project`
 Renames all `work` categories to `project`

### 3.10. Marking task(s) done : `mark / m`

Marks non-recurring task(s) as done and updates recurring task(s)' dates<br>
Format: `mark INDEX...`

> * Marks the task(s) as done at the specified `INDEX` if the task(s) is(are) non-recurring. <br>
> * Updates the start and end dates of the task(s) if the task(s) is(are) recurring based on the 
recurrance type (See details of recurrances in [**Add task**](#32-adding-a-task--add--a--plus-sign)). <br>
> * The index numbers refers to the index number last shown in the last task listing.<br>
> * The index numbers **must be a positive integer** (*e.g. 1, 2, 3, ...*).
> * Multiple indexes are allowed. All tasks at the specified indexes will be marked or updated.

Examples:
 
 * `list`<br>
  `mark 1 2`<br>
  Marks the first and second task as done if they are non-recurring. <br>
  Updates the dates of the first and second task if they are recurring. <br>
  
 * `find meeting`<br>
 `mark 1`<br>
 Marks the first task in the result of the `find` command as done if they are non-recurring. <br>
 Marks the first task in the result of the `find` command as done if they are non-recurring. <br>

### 3.11. Terminating task(s) : `terminate / t`

Terminates recurring task(s)<br>
Format: `terminate INDEX...`

> * Terminates the task(s) at the specified `INDEX` as long as the task(s) is(are) recurring. <br>
> * The index numbers refers to the index number last shown in the last task listing.<br>
> * The index numbers **must be a positive integer** (*e.g. 1, 2, 3, ...*).
> * Multiple indexes are allowed. All tasks at the specified indexes will be terminated.
> * Terminate will move the task(s) to the `Done` category

Examples:
 
 * `list`<br>
  `terminate 1 2`<br>
  Terminates the first and second recurrings tasks. 
  
 * `find meeting`<br>
  `terminate 1`<br>
 Terminates the first recurring task in the result of the `find`. <br>
 
### 3.12. Unmarking task(s) : `unmark / um`

Re-instates previously marked or terminated task(s)<br>
Format: `terminate INDEX...`

> * Unmarks the task(s) at the specified `INDEX`. <br>
> * If the the task(s) is(are) recurring, then their dates will be updated.<br>
> * The index numbers refers to the index number last shown in the last task listing.<br>
> * The index numbers **must be a positive integer** (*e.g. 1, 2, 3, ...*).
> * Multiple indexes are allowed. All tasks at the specified indexes will be unmarked.
> * Unmark will move the task(s) from the `Done` category to their initial categories from before they were marked or terminated.

Examples:
 
 * `list`<br>
  `unmark 1 2`<br>
  Unmarks the first and second tasks. 
  If any of the tasks is recurring, the it's dates will be updated
  
 * `find meeting`<br>
 `unmark 1`<br>
 Unmarks the first task in the result of the `find`. <br>
 If the task is recurring, it;s dates will be updated

### 3.13. Undoing a command : `undo / u`

Undoes a most recent command and reverts to previous state<br>
Format: `undo`

### 3.14. Redoing a command : `redo / r`

Redoes a most recent command after it has been undone<br>
Format: `redo`

### 3.15. Sorting tasks : `sort / s` 

Sorts tasks by their priorities<br>
Format: `sort p` 

Sorts tasks by their start dates<br>
Format: `sort sd`

Sorts tasks by their end dates<br>
Format: `sort ed`

### 3.16. Saving the data 

TaskBoss data will automatically be saved in local hard disk after entering any command that updates the data. There is no need to save manually.

## 3.16. Exporting the data : `save / sv`

Exports data to an existing filepath in xml format<br>
Format: `save FILE_PATH`

Creates a new xml file and exports data to that filepath<br>
Format: `save NEW_FILE_PATH`

TaskBoss loads data from the last specified filepath every time it is re-loaded.

### 3.17. Exiting the program : `exit / x`

Exits the program.<br>
Format: `exit`


## 4. FAQ

**_Q: How do I save my task data in TaskBoss?_** <br>
TaskBoss saves your data to ‘data/taskboss.xml’ by default whenever your task list is updated. There is no need to save manually. You can also change the storage location using the `save` command.

**_Q: How do I transfer my data to another computer?_** <br>
A: Install TaskBoss in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous TaskBoss.

## 5. Command Summary

<br>

|Command    |Parameters                                                        |
|:---------:|:-------------------------------------------------------------------------------------------|
|[help / h](#31-viewing-help--help--h)                       |**`help`**              |
|[add / a / +](#32-adding-a-task--add--a--plus-sign)                         |**`add TASK_NAME [i/INFO] [sd/START_DATE] [ed/END_DATE] [c/CATEGORY] [p/PRIORITY_LEVEL] [r/RECURRENCE]`**             |
|[list / l](#33-listing-all-tasks--list--l)                  |**`list` `list c/CATEGORY NAME`**        |
|[edit / e](#34-editing-a-task--edit--e)                     |**`edit INDEX [TASK NAME] [i/INFO] [sd/START_DATE] [ed/END_DATE] [c/CATEGORY] [p/PRIORITY_LEVEL] [r/RECURRENCE]`**|
|[find / f](#35-finding-tasks-by-keywords-or-by-datetime--find--f)|**`find KEYWORDS`  `find sd/date and time`  `find ed/date and time`**|
|[delete / d / -](#36-deleting-tasks--delete--d--minus-sign)                |**`delete INDEX...`**         |
|[clear / c](#37-clearing-tasks-by-category--clear--c)       |**`clear c/CATEGORY NAME`** |
|[view / v](#38-viewing-a-task--view--v)                    |**`view INDEX`**             |
|[name / n](#39-renaming-a-category--name--n)         |**`name EXISTING_CATEGORY NEW_CATEGORY`**       |
|[mark / m](#310-marking-tasks-done--mark--m)               |**`mark INDEX...`**        |
|[terminate / t](#311-terminating-tasks--terminate--t)              |**`terminate INDEX...`**      |
|[unmark / um](#312-unmarking-tasks--unmark--um)              |**`unmark INDEX...`**      |
|[undo / u](#313-undoing-a-command--undo--u)                 |**`undo`**            |
|[redo / r](#314-redoing-a-command--redo--r)                     |**`redo`**            |
|[sort / s](#315-sorting-tasks--sort--s)                     |**`sort ed` `sort sd` `sort p`**   |
|[save / sv](#316-exporting-the-data--save--sv)                |**`save FILE_PATH` `save NEW_FILE_PATH`**|
|[exit / x](#317-exiting-the-program--exit--x)               |**`exit`**           |

<br>
