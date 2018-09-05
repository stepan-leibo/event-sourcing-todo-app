import React, {Component} from 'react';
import {connect} from 'react-redux';
import TodoApi from "../utils/TodoApi";
import Project from "./Project";

class Projects extends Component {

    componentDidMount() {
        this.props.dispatch(TodoApi.getProjects());
    }

    render() {
        return (
            <div>
                {this.props.projects.map(project => (
                    <Project
                        key={project.id}
                        todoFilter={(currentProject, todos) => todos.filter(todo => currentProject.todoIds.includes(todo.id))}
                        isEnabled
                        projectName={project.name}
                        title={project.name}
                        projectId={project.id}
                        isCollapsed
                    />
                ))}
            </div>
        )
    }
}

function mapStateToProps({todo, project, admin}, ownProps) {
    return {
        projects: project.projects ? project.projects : []
    };
}

export default connect(mapStateToProps)(Projects);