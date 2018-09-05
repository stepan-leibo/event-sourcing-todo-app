import {createActions, handleActions} from "redux-actions";

export default handleActions({
    ADMIN: {
        ENABLE_ALL_TODOS: (state, action) => {
            return {
                ...state,
                isAllTodosEnabled: action.payload
            }
        },
        ENABLE_PROJECTS: (state, action) => {
            return {
                ...state,
                isProjectEnabled: action.payload
            }
        },
        ENABLE_STATS: (state, action) => {
            return {
                ...state,
                isStatsEnabled: action.payload
            }
        },
        ENABLE_NOTIFICATIONS: (state, action) => {
            return {
                ...state,
                isNotificationsEnabled: action.payload
            }
        },
        ENABLE_TODO_AUTO_GEN: (state, action) => {
            return {
                ...state,
                isTodoAutoGenEnabled: action.payload
            }
        },
        ENABLE_EVENTS: (state, action) => {
            return {
                ...state,
                isEventsEnabled: action.payload
            }
        }
    }
}, {
    isAllTodosEnabled: true,
    isProjectEnabled: false,
    isStatsEnabled: false,
    isNotificationsEnabled: false,
    isTodoAutoGenEnabled: false,
    isEventsEnabled: false,
});