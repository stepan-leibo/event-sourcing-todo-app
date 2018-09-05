import React, {Component} from 'react'
import TodoApi from "../utils/TodoApi";
import {connect} from "react-redux";
import {GoogleCharts} from 'google-charts';

class StatsChart extends Component {

    chart;
    interval;

    constructor() {
        super();

        GoogleCharts.load(() => {
            this.chart = new GoogleCharts.api.visualization.LineChart(document.getElementById('stats_chart'));
            this.props.dispatch(TodoApi.getStats());
        });
    }

    componentDidMount() {
        this.interval = setInterval(() => this.props.dispatch(TodoApi.getStats()), 1000);
    }

    componentDidUpdate() {
        this.redrawChart();
    }

    componentWillUnmount() {
        clearInterval(this.interval);
    }

    redrawChart() {
        if (this.chart) {
            let data = new GoogleCharts.api.visualization.DataTable();
            data.addColumn('number', 'X');
            data.addColumn('number', 'Todo');

            data.addRows(this.props.stats);

            let options = {
                hAxis: {
                    title: 'Time(min)',
                    logScale: false
                },
                vAxis: {
                    title: 'Number',
                    logScale: false
                },
                colors: ['#a52714', '#097138']
            };

            this.chart.draw(data, options);
        }
    }

    render() {
        return (
            <div id="stats_chart">
            </div>
        )
    }
}

function mapStateToProps({stat}) {
    return {
        stats: stat.stats ? stat.stats : []
    };
}

export default connect(mapStateToProps)(StatsChart);