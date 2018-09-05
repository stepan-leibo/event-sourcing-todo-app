import {handleActions} from "redux-actions";

export default handleActions({
    EVENT: {
        ADD_MULTIPLE: (state, action) => {
            let events = state.events ? state.events : [];
            return {
                ...state,
                events: [...events, ...action.payload].unique("id")
            }
        },
        ADD_ONE: (state, action) => {
            let events = state.events ? state.events : [];
            return {
                ...state,
                events: [...events, action.payload].unique("id")
            };
        }
    }
}, {});