import {projectActionCreators, todoActionCreators, statsActionCreators, eventsActionCreators} from "../actions";
import { toast } from 'react-toastify';

export default class TodoApi {
    static getTodos() {
        return (dispatch) => {
            fetch("http://localhost:6901/todos", { headers: TodoApi.getHeaders()})
                .then(TodoApi.handleErrors)
                .then(resp => resp.json())
                .then(items => dispatch(todoActionCreators.todo.addMultiple(items)))
                .catch(err => toast.error(err.message))
        }
    }
    static getTodo(id) {
        return (dispatch) => {
            fetch("http://localhost:6901/todos/" + id, { headers: TodoApi.getHeaders()})
                .then(TodoApi.handleErrors)
                .then(resp => resp.json())
                .then(item => dispatch(todoActionCreators.todo.addOne(item)))
                .catch(err => toast.error(err.message))
        }
    }

    static completeTodo(id) {
        let headers = TodoApi.getHeaders();
        headers.append('Content-type', 'application/json');
        return (dispatch) => {
            fetch("http://localhost:6901/todos/" + id + '/complete', {
                    headers: headers,
                    method: 'PUT'
                })
                .then(TodoApi.handleErrors)
                .catch(err => toast.error(err.message))
        }
    }

    static uncompleteTodo(id) {
        let headers = TodoApi.getHeaders();
        headers.append('Content-type', 'application/json');
        return (dispatch) => {
            fetch("http://localhost:6901/todos/" + id + '/uncomplete', {
                    headers: headers,
                    method: 'PUT'
                })
                .then(TodoApi.handleErrors)
                .catch(err => toast.error(err.message))
        }
    }

    static getProjects() {
        return (dispatch) => {
            fetch("http://localhost:6901/projects", { headers: TodoApi.getHeaders() })
                .then(TodoApi.handleErrors)
                .then(resp => resp.json())
                .then(items => dispatch(projectActionCreators.project.addMultiple(items)))
                .catch(err => toast.error(err.message))
        }
    }

    static getStats() {
        return (dispatch) => {
            fetch("http://localhost:6901/stats", { headers: TodoApi.getHeaders() })
                .then(TodoApi.handleErrors)
                .then(resp => resp.json())
                .then(items => dispatch(statsActionCreators.stat.addMultiple(items)))
                .catch(err => toast.error(err.message))
        }
    }

    static getEvents() {
        return (dispatch) => {
            fetch("http://localhost:6901/stats", { headers: TodoApi.getHeaders() })
                .then(TodoApi.handleErrors)
                .then(resp => resp.json())
                .then(items => dispatch(eventsActionCreators.event.addMultiple(items)))
                .catch(err => toast.error(err.message))
        }
    }

    static saveTodo(todo) {
        let headers = TodoApi.getHeaders();
        headers.append('Content-type', 'application/json');
        return (dispatch) => {
            let todoClosure = todo;
            fetch("http://localhost:6901/todos", {
                    headers: headers,
                    method: 'POST',
                    body: JSON.stringify(todo)
                })
                .then(TodoApi.handleErrors)
                .then(resp => resp.json())
                .then(todoId => {
                    todo.id = todoId;
                    dispatch(todoActionCreators.todo.addOne(todo));
                })
                .catch(err => toast.error(err.message))
        }
    }

    static saveProjectTodo(projectId, todo) {
        let headers = TodoApi.getHeaders();
        headers.append('Content-type', 'application/json');
        return (dispatch) => {
            let todoClosure = todo;
            let projectIdClosure = projectId;
            fetch("http://localhost:6901/todos", {
                    headers: headers,
                    method: 'POST',
                    body: JSON.stringify(todo)
                })
                .then(TodoApi.handleErrors)
                .then(resp => resp.json())
                .then(todoId => {
                    todo.id = todoId;
                    dispatch(todoActionCreators.todo.addOne(todo));
                    dispatch(TodoApi.addTodoToProject(projectIdClosure, todoId))
                })
                .catch(err => toast.error(err.message))
        }
    }

    static addTodoToProject(projectId, todoId) {
        let headers = TodoApi.getHeaders();
        headers.append('Content-type', 'application/json');
        return (dispatch) => {
            let todoIdClosure = todoId;
            let projectIdClosure = projectId;
            fetch(`http://localhost:6901/project/${projectIdClosure}/_addtodos`, {
                    headers: headers,
                    method: 'POST',
                    body: JSON.stringify([todoIdClosure])
                })
                .then(TodoApi.handleErrors)
                .then(resp => {
                    let project = {
                        id: projectIdClosure,
                        todoIds: [todoIdClosure]
                    };
                    dispatch(projectActionCreators.project.addTodos(project));
                })
                .catch(err => toast.error(err.message))
        }
    }

    static fetchUrl(url) {
        return fetch(url, {headers: TodoApi.getHeaders()})
    }

    static getHeaders() {
        let headers = new Headers();
        headers.append('Authorization', 'abcd');
        // headers.append("Access-Control-Request-Headers", "Content-Type, Authorization");
        // headers.append("Access-Control-Request-Method", "*");
        return headers;
    }

    static handleErrors(response) {
        if (!response.ok) {
            throw new Error(response.status);
        }
        return response;
    }
}