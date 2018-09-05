import React, {Component} from 'react';
import {connect} from 'react-redux';
import Switch from "./Switch";
import {adminActionCreators} from "../actions";

class Sidebar extends Component {

    toggleAllTodosEnabled() {
        this.props.dispatch(adminActionCreators.admin.enableAllTodos(!this.props.isAllTodosEnabled));
    }

    toggleProjectEnabled() {
        this.props.dispatch(adminActionCreators.admin.enableProjects(!this.props.isProjectEnabled));
    }

    toggleStatsEnabled() {
        this.props.dispatch(adminActionCreators.admin.enableStats(!this.props.isStatsEnabled));
    }

    toggleNotificationsEnabled() {
        this.props.dispatch(adminActionCreators.admin.enableNotifications(!this.props.isNotificationsEnabled));
    }

    toggleTodoAutoGenEnabled() {
        this.props.dispatch(adminActionCreators.admin.enableTodoAutoGen(!this.props.isTodoAutoGenEnabled));
    }

    toggleEventsEnabled() {
        this.props.dispatch(adminActionCreators.admin.enableEvents(!this.props.isEventsEnabled));
    }

    render() {
        return (
            <div className="sidebar">
                <div className="brand">
                    {/*<div className="pull-right">*/}
                        {/*<span className="glyphicon glyphicon-menu-hamburger menu-icon"/>*/}
                    {/*</div>*/}
                </div>
                <div className="sidebar-wrapper">
                    <ul className="nav">
                        <li>
                            <a>
                                <p>
                                    All todos&nbsp;
                                    <div className="pull-right sidebar-switch">
                                        <Switch onChange={this.toggleAllTodosEnabled.bind(this)} isOn={this.props.isAllTodosEnabled}/>
                                    </div>
                                </p>
                            </a>
                        </li>
                        <li>
                            <a>
                                <p>
                                    Events&nbsp;
                                    <div className="pull-right sidebar-switch">
                                        <Switch onChange={this.toggleEventsEnabled.bind(this)} isOn={this.props.isEventsEnabled}/>
                                    </div>
                                </p>
                            </a>
                        </li>
                        <li>
                            <a>
                                <p>
                                    Project&nbsp;
                                    <div className="pull-right sidebar-switch">
                                        <Switch onChange={this.toggleProjectEnabled.bind(this)} isOn={this.props.isProjectEnabled}/>
                                    </div>
                                </p>
                            </a>
                        </li>
                        <li>
                            <a>
                                <p>
                                    Statistics&nbsp;
                                    <div className="pull-right sidebar-switch">
                                        <Switch onChange={this.toggleStatsEnabled.bind(this)} isOn={this.props.isStatsEnabled}/>
                                    </div>
                                </p>
                            </a>
                        </li>
                        <li>
                            <a>
                                <p>
                                    Notifications&nbsp;
                                    <div className="pull-right sidebar-switch">
                                        <Switch onChange={this.toggleNotificationsEnabled.bind(this)} isOn={this.props.isNotificationsEnabled}/>
                                    </div>
                                </p>
                            </a>
                        </li>
                        <li>
                            <a>
                                <p>
                                    Todo autogeneration&nbsp;
                                    <div className="pull-right sidebar-switch">
                                        <Switch onChange={this.toggleTodoAutoGenEnabled.bind(this)} isOn={this.props.isTodoAutoGenEnabled}/>
                                    </div>
                                </p>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        )
    }
}

function mapStateToProps({admin}) {
    return admin;
}

export default connect(mapStateToProps)(Sidebar);