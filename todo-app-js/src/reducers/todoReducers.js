import {handleActions} from "redux-actions";

export default handleActions({
    TODO: {
        ADD_MULTIPLE: (state, action) => {
            let todos = state.todos ? state.todos : [];
            return {
                ...state,
                todos: [...todos, ...action.payload].unique("id")
            }
        },
        ADD_ONE: (state, action) => {
            let todos = state.todos ? state.todos : [];
            return {
                ...state,
                todos: [...todos, action.payload].unique("id")
            };
        }
    }
}, {});