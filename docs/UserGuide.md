# TaskBoss - User Guide

By : `Team W14-B2`  &nbsp;&nbsp;&nbsp;&nbsp; Since: `Mar 2017`  &nbsp;&nbsp;&nbsp;&nbsp; Licence: `MIT`

---

1. [Introduction](#1-introduction)
2. [Quick Start](#2-quick-start)
3. [Features](#3-features)<br>
    3.1 [Viewing help](#31-viewing-help--help--h)<br>
    3.2 [Adding a task](#32-adding-a-task--add--a--plus-sign)<br>
    3.3 [Listing all tasks](#33-listing-all-tasks--list--l)<br>
    3.4 [Editing a task](#34-editing-a-task--edit--e)<br>
    3.5 [Finding task(s) by keywords or by datetime](#35-finding-tasks-by-keywords-or-by-datetime--find--f)<br>
    3.6 [Deleting task(s)](#36-deleting-tasks--delete--d--minus-sign)<br>
    3.7 [Clearing tasks by category](#37-clearing-tasks-by-category--clear--c)<br>
    3.8 [Viewing a task](#38-viewing-a-task--view--v)<br>
    3.9 [Renaming a category](#39-renaming-a-category--name--n)<br>
    3.10 [Marking task(s) done](#310-marking-tasks-done--mark--m)<br>
    3.11 [Terminating task(s)](#311-terminating-tasks--terminate--t)<br> 
    3.12 [Unmarking task(s)](#312-unmarking-tasks--unmark--um)<br> 
    3.13 [Undoing a command](#313-undoing-a-command--undo--u)<br>
    3.14 [Redoing a command](#314-redoing-a-command--redo--r)<br> 
    3.15 [Sorting tasks](#315-sorting-tasks--sort--s)<br>
    3.16 [Saving the data](#316-saving-the-data--save--sv)<br>
    3.17 [Exiting the program](#317-exiting-the-program--exit--x)<br>
4. [FAQ](#4-faq)
5. [Command Summary](#5-command-summary)
 

## TaskBoss Prototype
<img src="images/TaskBoss_UI.png" width="600"> <br>
An image of TaskBoss

## 1. Introduction
Have you ever been overwhelmed with too many tasks? Perhaps a couple of these tasks might have slipped your mind. Well, TaskBoss is here to help you out! TaskBoss is a user friendly task manager which supports a wide range of features.<br>
<br>
Be the boss of your tasks, use TaskBoss today!

## 2. Quick Start

### 2.1. Download
1. Ensure you have Java version `1.8.0_60` or later installed in your Computer.<br>
2. Download the latest `TaskBoss.jar` from the [releases](../../../releases) tab as shown in figure 1.<br>
<p align="left"><img src="images/release_taskboss.png" width="400"></p>
<h5 align="left">Figure 1: Locating TaskBoss.jar in the Releases Tab</h5>
3. Copy the jar file to the folder you want to use as the home folder for TaskBoss.

### 2.2. Launch
To launch TaskBoss, double-click on `TaskBoss.jar`. The Graphical User Interface (GUI) should appear in a few seconds.<br>
Figure 2 below shows the sample GUI of TaskBoss pre-loaded with tasks.
<p align="left"><img src="images/TaskBoss_UI.png" width="800"></p>
<h5 align="left">Figure 2: Sample GUI of TaskBoss Pre-loaded with Tasks</h5>

### 2.2. Visual Introduction
<p align="left"><img src="images/visual_intro_taskboss.png" width="800"></p>
<h5 align="left">Figure 2: Main Components of the GUI of TaskBoss</h5>

1. **Category List Panel**<br>
Displays a list of all categories created by the user when adding tasks to TaskBoss. The highlighted category is the current showing category in TaskBoss.
    * Note that Alltasks and Done categories are built-in categories of TaskBoss and are labelled with a different colour.
    * When the user adds a task, the task is automatically included in Alltasks category.
    * When the user marks a task done, the task is automatically moved to Done category.

2. **Task List Panel**<br>
Displays a list of tasks under a particular category, if specified. Otherwise, it displays all tasks that exist in Taskboss.

3. **Command Box**<br>
Provides a box where user commands are entered.

4. **Result Display**<br>
Displays result messages and user feedback.

5. **Status Bar**<br>
Displays the filepath where TaskBoss' data is currently saved in.

## 3. Features

**List of Commands**

|Command    |Description                                                          |
|:---------:|:-------------------------------------------------------------------:|
|[**help / h**](#31-viewing-help--help--h)                       |View help              |
|[**add / a / +**](#32-adding-a-task--add--a--plus-sign)                         |Add a task             |
|[**list / l**](#33-listing-all-tasks--list--l)                  |List the tasks         |
|[**edit / e**](#34-editing-a-task--edit--e)                     |Edit a task            |
|[**find / f**](#35-finding-tasks-by-keywords-or-by-datetime--find--f)           |Find tasks by keywords or by datetime             |
|[**delete / d / -**](#36-deleting-tasks--delete--d--minus-sign)                |Delete a task          |
|[**clear / c**](#37-clearing-tasks-by-category--clear--c)       |Clear tasks in category|
|[**view / v**](#38-viewing-a-task--view--v)                    |View a task            |
|[**name / n**](#39-renaming-a-category--name--n)         |Rename a category      |
|[**mark / m**](#310-marking-tasks-done--mark--m)               |Mark a task as done and updates task's dates    |
|[**terminate / t**](#311-terminating-tasks--terminate--t)          |Terminates a recurring task    |
|[**unmark / um**](#312-unmarking-tasks--unmark--um)       |Unmarks previously marked or terminated tasks and updates task's dates | 
|[**undo / u**](#313-undoing-a-command--undo--u)                 |Undo a task            |
|[**redo / r**](#314-redoing-a-command--redo--r)                 |Redo a task            |
|[**sort / s**](#315-sorting-tasks--sort--s)                     |Sort tasks by deadline or by priority|
|[**save / sv**](#316-exporting-the-data--save--sv)                |Save TaskBoss          |
|[**exit / x**](#317-exiting-the-program--exit--x)               |Exit TaskBoss          |

**General Command Format**
>
> * Words in `UPPER_CASE` are the parameters.
> * Items in `SQUARE_BRACKETS` are optional.
> * Items with `...` after them can have multiple instances.
> * Parameters can be in any order.
<br>

### 3.1. Viewing help : `help / h`

Format: `help`

### 3.2. Adding a task : `add / a / +`

Adds a task<br>
Format: `add TASK_NAME [i/INFO] [sd/START_DATE] [ed/END_DATE] [c/CATEGORY...] [p/PRIORITY_LEVEL] [r/RECURRENCE]`

> * The added task is included in `Alltasks` category by default.
> * Task name and information can take both alphanumeric and non-alphanumeric characters.
> * Start and end dates are automatically formatted into `Month Day_of_month, Year Time`, `i.e Apr 14, 2017`.
> * Priority level is `no` by default, and can be either `yes` or `no` OR `y` or `n` (case-insensitive).
> * Category is configured to be in the lowercase form with the first letter in the uppercase form. `i.e c/work` will be `Work`.
> * Recurrence is `none` by default, and can be either `daily`, `weekly`, `monthly` or `yearly` (case-insensitive).
> * All prefixes are case-sensitive.<br>
  - prefixes include i/, sd/, ed/, c/, p/, and r/.

Examples:

* `add Email progress report ed/19-02-2017 c/Project p/YES r/weekly`
* `add Dinner with Jim i/In Orchard road sd/next friday ed/19-02-2017 c/Meeting p/no`
* `add Contract signing sd/tomorrow at 3 PM c/Work i/company merger`

#### 3.2.1. Accepted Date Format
Date can be either in the UK format `i.e dd-mm-yyyy` or the US format `i.e mm-dd-yyyy` depending on the region and date settings of your computer. It can also be in natural language `i.e this sunday` and is case-insensitive.<br>
Table 1 below shows some examples of the accepted date format.

| Date Format     | Example(s)           | Formatted Result   |
|-----------------|----------------------|--------------------|
| Month/day       | 3/19                 | Mar 19, 2017       |
| Day Month       | 1 Dec                | Dec 01, 2017       |
| Month Day       | Nov 1                | Nov 01, 2017       |
| Day of the week | thurs, Thursday      | (relative)         |
| Relative date   | today, tmr, next fri | (relative)          |
<h5 align="left">Table 1: Examples of Accepted Date Format</h5>
> * Table 1 above assumes date inputs in the UK format

#### 3.2.2. Accepted Time Format
Time should be in the 24-hour clock format `i.e 1830`, or the 12-hour clock format with `AM` or `PM` (case-insensitive) `i.e 6:30 PM`.<br>
Table 2 below shows some examples of the accepted time format.
 
| Time Format     | Example(s)                              | Formatted Result   |
|-----------------|-----------------------------------------|--------------------|
| 24-hour         | 22                                      | 10:00 PM           |
| 12-hour         | 7.20pm                                  |  7:20 PM           |
| Hour:Minute     | 10:30                                   | 10:30 AM           |
| Hour.Minute     | 10.30                                   | 10.30 AM           |
| Relative time   | this morning, this afternoon, tonight   | (relative)         |
<h5 align="left">Table 1: Examples of Accepted Time Format</h5>

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
  
### 3.6. Deleting task(s) : `delete / d / -`

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
  `t 1 2`<br>
  Terminates the first and second recurrings tasks. 
  
 * `find meeting`<br>
 `mark 1`<br>
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

 There is no need to save manually.

### 3.16. Saving the data : `save / sv`

Saves data in _taskboss.xml_ to an existing filepath<br>
Format: `save EXISTING_FILE_PATH`

Creates a new filepath and saves data in _taskboss.xml_ to that filepath<br>
Format: `save NEW_FILE_PATH`

> * TaskBoss saves data in _taskboss.xml_ in its home folder by default if the `save` command is not used.
> * _taskboss.xml_ updates itself automatically after the user enters any command that changes the data.
> * TaskBoss loads data from the last specified filepath every time it is re-loaded.

### 3.18. Exiting the program : `exit / x`

Exits the program.<br>
Format: `exit`


## 4. FAQ

**_Q: How do I save my task data in TaskBoss?_** <br>
TaskBoss saves your data to ‘data/taskboss.xml’ by default whenever your task list is updated. There is no need to save manually. You can also change the storage location using the `save` command.

**_Q: How do I transfer my data to another computer?_** <br>
A: Download TaskBoss in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous TaskBoss.

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
