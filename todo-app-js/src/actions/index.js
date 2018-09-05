import {createActions} from 'redux-actions';

export const todoActionCreators = createActions({
    TODO: {
        ADD_MULTIPLE: items => items,
        ADD_ONE: item => item
    }
});

export const projectActionCreators = createActions({
    PROJECT: {
        ADD_MULTIPLE: items => items,
        ADD_ONE: item => item,
        ADD_TODOS: item => item,
        DELETE_TODOS: item => item,
    }
});

export const statsActionCreators = createActions({
    STAT: {
        ADD_MULTIPLE: items => items,
    }
});

export const eventsActionCreators = createActions({
    EVENT: {
        ADD_MULTIPLE: items => items,
        ADD_ONE: item => item
    }
});

export const adminActionCreators = createActions({
    ADMIN: {
        ENABLE_ALL_TODOS: isEnabled => isEnabled,
        ENABLE_PROJECTS: isEnabled => isEnabled,
        ENABLE_STATS: isEnabled => isEnabled,
        ENABLE_NOTIFICATIONS: isEnabled => isEnabled,
        ENABLE_TODO_AUTO_GEN: isEnabled => isEnabled,
        ENABLE_EVENTS: isEnabled => isEnabled,
    }
});