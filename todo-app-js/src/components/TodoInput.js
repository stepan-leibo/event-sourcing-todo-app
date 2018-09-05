import React, {Component} from 'react'
import TodoApi from "../utils/TodoApi";
import PropTypes from 'prop-types';
import Todo from '../data/Todo'
import {connect} from "react-redux";

class TodoInput extends Component {

    static propTypes = {
        projectId: PropTypes.string
    }

    state = {
        todoText: ""
    };

    handleTodoText(e) {
        this.setState({
            todoText: e.target.value
        })
    }

    handleKey(e) {
        if (e.key === 'Enter' && this.state.todoText) {
            if (this.props.projectId) {
                this.props.dispatch(TodoApi.saveProjectTodo(this.props.projectId, new Todo(this.state.todoText)));
            } else {
                this.props.dispatch(TodoApi.saveTodo(new Todo(this.state.todoText)));
            }
            this.setState({
                todoText: ""
            })
        }
    }

    render() {
        return (
            <div className="todo-item footer todo-input">
                <input
                    type="text"
                    className="form-control"
                    value={this.state.todoText}
                    onChange={this.handleTodoText.bind(this)}
                    onKeyPress={this.handleKey.bind(this)}
                />
            </div>
        )
    }
}

export default connect()(TodoInput);