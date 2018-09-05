import React, {Component} from 'react';
import {connect} from 'react-redux';
import {todoActionCreators} from "../actions";
import TodoApi from "../utils/TodoApi";
import PropTypes from 'prop-types';

class TodoItem extends Component {

    static propTypes = {
        id: PropTypes.string.isRequired
    };

    handleCompletedChange(e) {
        let {checked} = e.target;
        let todo = Object.assign({completed: checked}, this.props.todo);
        todo.completed = checked;
        this.props.dispatch(todoActionCreators.todo.addOne(todo));
        if (checked) {
            this.props.dispatch(TodoApi.completeTodo(todo.id));
        } else {
            this.props.dispatch(TodoApi.uncompleteTodo(todo.id));
        }
    }

    shouldComponentUpdate(nextProps, nextState) {
        return !(this.props.todo.id === nextProps.todo.id
            && this.props.todo.text === nextProps.todo.text
            && this.props.todo.completed === nextProps.todo.completed)
    }

    render() {
        let itemClass = this.props.todo.completed ? "todo-item completed" : "todo-item";
        let labelClass = this.props.todo.completed ? "checkbox checked" : "checkbox";
        return (
            <div className={itemClass}>
                <div className="todo-item-wrapper">
                    <label className={labelClass}>
                        <span className="icons">
                            <img className="first-icon" src="/crystal/media/checkbox-uncheck.47f0d924.svg" width="17"/>
                            <img className="second-icon" src="/crystal/media/checkbox-check.27fcc532.svg" width="17"/>
                        </span>
                        <input type="checkbox" data-toggle="checkbox" checked={this.props.todo.completed} onChange={this.handleCompletedChange.bind(this)}/>
                    </label>
                    <div className="todo-content">{this.props.todo.text}</div>
                </div>
            </div>
        )
    }
}

function mapStateToProps({todo}, ownProps) {
    return {
        todo: todo.todos
            ? todo.todos.find(item => item.id === ownProps.id)
            : null
    };
}

export default connect(mapStateToProps)(TodoItem);