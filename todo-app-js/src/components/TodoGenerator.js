import React, {Component} from 'react';
import {connect} from 'react-redux';
import {doEverySeconds} from "../utils/Utils";
import TodoApi from "../utils/TodoApi";
import Todo from "../data/Todo";

let randomWords = require('random-words');

class TodoGenerator extends Component {

    componentDidMount() {
        doEverySeconds(
            1,
            5,
            () => {
                this.props.dispatch(TodoApi.saveTodo(new Todo(randomWords({ min: 1, max: 5 }).join(' '))));
            },
            (timeoutId) => {this.setState({timeoutId: timeoutId});} );
    }

    componentWillUnmount() {
        clearTimeout(this.state.timeoutId);
    }

    render() {
        return (
            <div>
            </div>
        )
    }
}

export default connect()(TodoGenerator);