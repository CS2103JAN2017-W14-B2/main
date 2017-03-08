package seedu.taskboss.model.task;

import seedu.taskboss.model.category.UniqueCategoryList;

/**
 * A read-only immutable interface for a Task in TaskBoss.
 * Implementations should guarantee: details are present and not null, field values are validated.
 */
public interface ReadOnlyTask {

    Name getName();
    PriorityLevel getPriorityLevel();
    Information getInformation();

    /**
     * The returned CategoryList is a deep copy of the internal CategoryList,
     * changes on the returned list will not affect the task's internal categories.
     */
    UniqueCategoryList getCategories();

    /**
     * Returns true if both have the same state. (interfaces cannot override .equals)
     */
    default boolean isSameStateAs(ReadOnlyTask other) {
        return other == this // short circuit if same object
                || (other != null // this is first to avoid NPE below
                && other.getName().equals(this.getName()) // state checks here onwards
                && other.getPriorityLevel().equals(this.getPriorityLevel())
                && other.getInformation().equals(this.getInformation()));
    }

    /**
     * Formats the task as text, showing all contact details.
     */
    default String getAsText() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Priority Level: ")
                .append(getPriorityLevel())
                .append(" Information: ")
                .append(getInformation())
                .append(" Categories: ");
        getCategories().forEach(builder::append);
        return builder.toString();
    }

}