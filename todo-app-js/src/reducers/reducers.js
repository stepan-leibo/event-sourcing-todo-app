import {combineReducers} from 'redux';
import projectReducer from './projectReducers'
import todoReducer from './todoReducers'
import statsReducer from './statsReducers'
import eventReducer from './eventsReducers'
import adminReducers from "./adminReducers";


export default combineReducers({
    project: projectReducer,
    todo: todoReducer,
    stat: statsReducer,
    event: eventReducer,
    admin: adminReducers
});