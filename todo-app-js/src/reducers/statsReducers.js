import {handleActions} from "redux-actions";

export default handleActions({
    STAT: {
        ADD_MULTIPLE: (state, action) => {
            let statsObj = action.payload ? action.payload : {};
            let stats = [];
            Object.keys(action.payload).map(function(key, index) {
                stats.push([index, action.payload[key]]);
            });

            return {
                ...state,
                stats: stats
            }
        }
    }
}, {});