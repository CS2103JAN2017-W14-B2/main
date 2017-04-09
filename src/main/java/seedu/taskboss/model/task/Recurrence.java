package seedu.taskboss.model.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import seedu.taskboss.commons.exceptions.IllegalValueException;
import seedu.taskboss.model.category.Category;
import seedu.taskboss.model.category.UniqueCategoryList;

//@@author A0143157J
public class Recurrence {

    private static final String EMPTY_STRING = "";
    private static final String CATEGORY_DONE = "Done";
    private static final int AMOUNT_ONE = 1;

    public static final String MESSAGE_RECURRENCE_CONSTRAINTS =
            "Task recurrence is NONE by default,"
            + " and can be DAILY, WEEKLY, MONTHLY or YEARLY.";

    public enum Frequency {
        DAILY, WEEKLY, MONTHLY, YEARLY, NONE
    }

    private Frequency frequency;

    public Recurrence(Frequency frequency) throws IllegalArgumentException {
        assert frequency != null;
        if (frequency.toString().equals(EMPTY_STRING)) {
            this.frequency = Frequency.NONE;
        } else {
            this.frequency = frequency;
        }
    }

    /**
     * Marks a recurring {@code task} undone and
     * updates {@code task} dates according to the recurrence of the {@code task}
     * @throws IllegalValueException
     */
    public void updateTaskDates(Task task) throws IllegalValueException {
        markTaskUndone(task);

        Date startDate = task.getStartDateTime().getDate();
        Date endDate = task.getEndDateTime().getDate();
        SimpleDateFormat startSdfFormat = initSimpleDateFormat(task.getStartDateTime());
        SimpleDateFormat endSdfFormat = initSimpleDateFormat(task.getEndDateTime());
        boolean isStartDate = true;

        switch(this.frequency) {
        case DAILY:
            updateDailyRecur(task, startDate, endDate, startSdfFormat, endSdfFormat, isStartDate);
            break;

        case WEEKLY:
            updateWeeklyRecur(task, startDate, endDate, startSdfFormat, endSdfFormat, isStartDate);
            break;

        case MONTHLY:
            updateMonthlyRecur(task, startDate, endDate, startSdfFormat, endSdfFormat, isStartDate);
            break;

        case YEARLY:
            updateYearlyRecur(task, startDate, endDate, startSdfFormat, endSdfFormat, isStartDate);
            break;

        case NONE:
            break; // do nothing

        default:
            throw new IllegalValueException(Recurrence.MESSAGE_RECURRENCE_CONSTRAINTS);
        }
    }

    /**
     * Update task dates {@code startDate} and {@code endDate} by incrementing a year if they are present
     * @throws IllegalValueException
     */
    private void updateYearlyRecur(Task task, Date startDate, Date endDate, SimpleDateFormat startSdfFormat,
            SimpleDateFormat endSdfFormat, boolean isStartDate) throws IllegalValueException {
        if (startDate != null) {
            updateDateTime(Frequency.YEARLY, task, startSdfFormat, isStartDate);
        }
        if (endDate != null) {
            updateDateTime(Frequency.YEARLY, task, endSdfFormat, !isStartDate);
        }
    }

    /**
     * Update task dates {@code startDate} and {@code endDate} by incrementing a month if they are present
     * @throws IllegalValueException
     */
    private void updateMonthlyRecur(Task task, Date startDate, Date endDate, SimpleDateFormat startSdfFormat,
            SimpleDateFormat endSdfFormat, boolean isStartDate) throws IllegalValueException {
        if (startDate != null) {
            updateDateTime(Frequency.MONTHLY, task, startSdfFormat, isStartDate);
        }
        if (endDate != null) {
            updateDateTime(Frequency.MONTHLY, task, endSdfFormat, !isStartDate);
        }
    }

    /**
     * Update task dates {@code startDate} and {@code endDate} by incrementing a weekly if they are present
     * @throws IllegalValueException
     */
    private void updateWeeklyRecur(Task task, Date startDate, Date endDate, SimpleDateFormat startSdfFormat,
            SimpleDateFormat endSdfFormat, boolean isStartDate) throws IllegalValueException {
        if (startDate != null) {
            updateDateTime(Frequency.WEEKLY, task, startSdfFormat, isStartDate);
        }
        if (endDate != null) {
            updateDateTime(Frequency.WEEKLY, task, endSdfFormat, !isStartDate);
        }
    }

    /**
     * Update {@code task} dates {@code startDate} and {@code endDate} by incrementing a day if they are present
     * @throws IllegalValueException
     */
    private void updateDailyRecur(Task task, Date startDate, Date endDate, SimpleDateFormat startSdfFormat,
            SimpleDateFormat endSdfFormat, boolean isStartDate) throws IllegalValueException {
        if (startDate != null) {
            updateDateTime(Frequency.DAILY, task, startSdfFormat, isStartDate);
        }
        if (endDate != null) {
            updateDateTime(Frequency.DAILY, task, endSdfFormat, !isStartDate);
        }
    }

    /**
     * Update a {@code task} date based on the specified {@code frequency}
     * @throws IllegalValueException
     */
    private void updateDateTime(Frequency frequency, Task task, SimpleDateFormat sdfFormat, boolean isStartDate)
            throws IllegalValueException {
        if (isStartDate) {
            Date startDate = task.getStartDateTime().getDate();
            Calendar startCalendar = addFrequencyToCalendar(startDate, frequency);
            setDateTime(startCalendar, task, sdfFormat, true);
        } else {
            Date endDate = task.getEndDateTime().getDate();
            Calendar endCalendar = addFrequencyToCalendar(endDate, frequency);
            setDateTime(endCalendar, task, sdfFormat, false);
        }
    }

    /**
     * Set the start/end DateTime of a given {@code task} with the Date
     * extracted from {@code calendar}, based on its original SimpleDateFormat {@code desiredFormat}
     * @throws IllegalValueException
     */
    private void setDateTime(Calendar calendar, Task task,
            SimpleDateFormat desiredFormat, boolean isStartDate) throws IllegalValueException {
        String dateInString = desiredFormat.format(calendar.getTime());
        if (isStartDate) {
            task.setStartDateTime(new DateTime(dateInString));
        } else {
            task.setEndDateTime(new DateTime(dateInString));
        }
    }

    /**
     * Constructs a Calendar and adds the corresponding
     * frequency to the Calendar with the given {@code date}.
     */
    private Calendar addFrequencyToCalendar(Date date, Frequency freq) {
        Calendar calendar = initCalendar(date);
        if (freq == Frequency.DAILY) {
            addDayToCalendar(calendar);
        } else if (freq == Frequency.WEEKLY) {
            addWeekToCalendar(calendar);
        } else if (freq == Frequency.MONTHLY) {
            addMonthToCalendar(calendar);
        } else if (freq == Frequency.YEARLY) {
            addYearToCalendar(calendar);
        }
        return calendar;
    }

    /**
     * Adds one day to the {@code calendar}.
     */
    private void addDayToCalendar(Calendar calendar) {
        calendar.add(Calendar.DATE, AMOUNT_ONE);
    }

    /**
     * Adds one week to the {@code calendar}.
     */
    private void addWeekToCalendar(Calendar calendar) {
        calendar.add(Calendar.WEEK_OF_YEAR, AMOUNT_ONE);
    }

    /**
     * Adds one month to the {@code calendar}.
     */
    private void addMonthToCalendar(Calendar calendar) {
        calendar.add(Calendar.MONTH, AMOUNT_ONE);
    }

    /**
     * Adds one year to the {@code calendar}.
     */
    private void addYearToCalendar(Calendar calendar) {
        calendar.add(Calendar.YEAR, AMOUNT_ONE);
    }

    /**
     * Returns an initialised calendar with the given Date
     */
    private Calendar initCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * Returns the corresponding SimpleDateFormat of the given DateTime
     */
    private SimpleDateFormat initSimpleDateFormat(DateTime dateTime) {
        if (dateTime.isTimeInferred()) {
            return new SimpleDateFormat("MMM dd, yyyy");
        } else {
            return new SimpleDateFormat("MMM dd, yyyy h:mm aa");
        }
    }

    /**
     * Marks a task as undone (i.e removing it from "Done" category)
     * @throws IllegalValueException
     */
    private void markTaskUndone(Task task) throws IllegalValueException {
        UniqueCategoryList newCategoryList = new UniqueCategoryList();
        for (Category category : task.getCategories()) {
            if (!category.equals(CATEGORY_DONE)) {
                newCategoryList.add(category);
            }
        }
        task.setCategories(newCategoryList);
    }

    public boolean isRecurring() {
        return this.frequency != Frequency.NONE;
    }

    @Override
    public String toString() {
        switch(this.frequency) {
        case DAILY :
            return "DAILY";
        case WEEKLY:
            return "WEEKLY";
        case MONTHLY:
            return "MONTHLY";
        case YEARLY:
            return "YEARLY";
        default:
            return "NONE";
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Recurrence // instanceof handles nulls
                && this.frequency.equals(((Recurrence) other).frequency)); // state check
    }

    /**
     * Returns true if a given string is a valid task recurrence.
     * Created for testing purposes only.
     */
    public static boolean isValidRecurrence(String inputFreq) {
        String freq = inputFreq.toUpperCase().trim();
        return (freq.equals("DAILY") || freq.equals("WEEKLY") ||
                freq.equals("MONTHLY") || freq.equals("YEARLY") ||
                freq.equals("NONE") || freq.equals(EMPTY_STRING));
    }
}
