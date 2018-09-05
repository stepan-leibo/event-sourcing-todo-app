import {handleActions} from "redux-actions";

export default handleActions({
    PROJECT: {
        ADD_MULTIPLE: (state, action) => {
            let projects = state.projects ? state.projects : [];
            return {
                ...state,
                projects: [...projects, ...action.payload].unique("id")
            }
        },
        ADD_ONE: (state, action) => {
            let projects = state.projects ? state.projects : [];
            return {
                ...state,
                projects: [...projects, action.payload].unique("id")
            };
        },
        ADD_TODOS: (state, action) => {
            let project = state.projects ? state.projects.find(item => item.id === action.payload.id) : null;
            if (project) {
                project.todoIds = [...project.todoIds, ...action.payload.todoIds];
                return {
                    ...state,
                    projects: [...state.projects, project].unique("id")
                };
            }

            return state;
        },
        DELETE_TODOS: (state, action) => {
            let project = state.projects ? state.projects.find(item => item.id === action.payload.id) : null;
            if (project) {
                project.todoIds = project.todoIds.filter(todoId => !action.payload.todoIds.includes(todoId));
                return {
                    ...state,
                    projects: [...state.projects, project].unique("id")
                };
            }

            return state;
        }
    }
}, {});