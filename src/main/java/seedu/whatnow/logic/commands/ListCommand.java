//@@author A0139772U
package seedu.whatnow.logic.commands;

/**
 * Lists all tasks in WhatNow to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all tasks";
    
    public static final String COMPLETE_MESSAGE_SUCCESS = "Listed all completed tasks";

    public static final String INCOMPLETE_MESSAGE_SUCCESS = "Listed all incomplete tasks";
    
    public static final String TASK_STATUS_DONE = "done";

    public static final String TASK_STATUS_COMPLETED = "completed";

    public static final String TASK_STATUS_ALL = "all";

    public static final String TASK_STATUS_INCOMPLETE = "incomplete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": the task specified\n" + "Parameters: done\n"
            + "Examples: " + COMMAND_WORD + " done or " + COMMAND_WORD + " all or " + COMMAND_WORD;

    public String type;

    public ListCommand(String type) {
        this.type = type;
    }

    private void mapInputToCorrectArgumentForExecution() {
        if (type.equals(TASK_STATUS_DONE)) {
            type = TASK_STATUS_COMPLETED;
        } else if (type.equals(TASK_STATUS_ALL)) {
            type = TASK_STATUS_ALL;
        } else {
            type = TASK_STATUS_INCOMPLETE;
        }
    }

    /**
     * Executes the ListCommand to display task based on task status(incomplete or complete or both) 
     */
    @Override
    public CommandResult execute() {
        mapInputToCorrectArgumentForExecution();
        model.updateFilteredScheduleListToShowAllOverdue();
        if (type.equals(TASK_STATUS_ALL)) {
            model.updateFilteredListToShowAll();
            model.updateFilteredScheduleListToShowAll();
            model.getUndoStack().push(COMMAND_WORD);
            model.getStackOfListTypes().push(TASK_STATUS_ALL);
            return new CommandResult(MESSAGE_SUCCESS);
        } else if (type.equals(TASK_STATUS_INCOMPLETE)) {
            model.updateFilteredListToShowAllIncomplete();
            model.updateFilteredScheduleListToShowAllIncomplete();
            model.getUndoStack().push(COMMAND_WORD);
            model.getStackOfListTypes().push(TASK_STATUS_INCOMPLETE);
            return new CommandResult(INCOMPLETE_MESSAGE_SUCCESS);
        } else {
            model.updateFilteredListToShowAllCompleted();
            model.updateFilteredScheduleListToShowAllCompleted();
            model.getUndoStack().push(COMMAND_WORD);
            model.getStackOfListTypes().push(TASK_STATUS_COMPLETED);
            return new CommandResult(COMPLETE_MESSAGE_SUCCESS);
        }
    }
}
