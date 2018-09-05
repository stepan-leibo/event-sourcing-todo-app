import React, {Component} from 'react';
import {connect} from 'react-redux';
import PropTypes from 'prop-types';
import TodoItem from "./TodoItem";
import TodoApi from "../utils/TodoApi";
import TodoInput from "./TodoInput";
import sortBy from 'sort-by';

class Project extends Component {

    static propTypes = {
        projectId: PropTypes.string,
        title: PropTypes.string.isRequired,
        projectName: PropTypes.string.isRequired,
        todoFilter: PropTypes.func.isRequired,
        isEnabled: PropTypes.bool,
        isCollapsed: PropTypes.bool
    };

    state = {
        isCollapsed: false
    };

    componentDidMount() {
        // todo: remove and fetch other way
        this.props.dispatch(TodoApi.getTodos());
        this.setState({
            isCollapsed: !!this.props.isCollapsed
        })
    }

    toggleDisplayContent() {
        this.setState({
            isCollapsed: !this.state.isCollapsed
        })
    }

    render() {
        let chevron = !this.state.isCollapsed ? "glyphicon-chevron-down" : "glyphicon-chevron-up";
        return (
            <div>
                {this.props.isProjectEnabled && (
                    <div>
                        <div className="data-container">
                            <div className="card">
                                <div className="header">
                                    <h3>{this.props.title} ({this.props.todos.length})</h3>
                                    <span className={`glyphicon ${chevron} pull-right project-icon`} aria-hidden="true" onClick={this.toggleDisplayContent.bind(this)}/>
                                </div>
                                <div className="content">
                                    {!this.state.isCollapsed && (
                                        <form>
                                            {this.props.todos.map(todo => (
                                                <TodoItem key={todo.id} id={todo.id}/>
                                            ))}
                                        </form>
                                    )}
                                </div>
                            </div>
                        </div>
                        {this.state.isCollapsed && (
                            <div className="card-no-content"/>
                        )}
                        {!this.state.isCollapsed && (
                            <TodoInput projectId={this.props.project ? this.props.project.id : null}/>
                        )}
                    </div>
                )}
            </div>
        )
    }
}

function mapStateToProps({todo, project, admin}, ownProps) {
    let currentProject = project.projects ? project.projects.find(item => item.id === ownProps.projectId) : null;
    let todos = ownProps.todoFilter(currentProject, todo.todos ? todo.todos : []).sort(sortBy('completed', 'text'));
    return {
        todos: todos,
        project: currentProject,
        isProjectEnabled: admin.isProjectEnabled || ownProps.isEnabled
    };
}

export default connect(mapStateToProps)(Project);