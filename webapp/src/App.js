import React from "react";
import reservationImg from './reservation.svg';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Button, Card, Col, Container, Form, ListGroup, Row, Image, Alert, FormLabel} from "react-bootstrap";
import {default as axios} from "axios";


class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            data: [],
            name: "",
            lastName: "",
            insurance_code: "",
            phone: "",
            email: "",
            id_person: 0,
            name2: "",
            description: "",
            Myyear: 0,
            Mymonth: 0,
            Myday: 0,
            Myhour: "",
            error: {},


        };
    }

    componentDidMount() {
        const axios = require('axios').default;
        axios.get('http://localhost:8080/visits')
            .then((response) => {
                this.setState({
                    'data': response.data
                })
                //console.log(response.data);
                //console.log(response.status);
                //console.log(response.statusText);
                //console.log(response.headers);
                //console.log(response.config);
            });
        //console.log(this.state.response);
        //console.log(this.state.error);
    }

    onChange = (e, name) => {
        //console.log("---", e, name)
        this.setState({
            [name]: e
        })
    }

    createNew = (e) => {
        e.preventDefault();
        //   e.stopPropagation();
        //  e.nativeEvent.stopImmediatePropagation();
        const axios = require('axios').default;
        axios({
            method: 'post',
            url: 'http://localhost:8080/visits/create',
            data: {
                "person": {
                    "name": this.state.name,
                    "lastName": this.state.lastName,
                    "phone": this.state.phone,
                    "email": this.state.email,
                    "insuranceCode": this.state.insurance_code
                },

                "reason": {
                    "description": this.state.description,
                    "name": this.state.name2
                },

                "visit": {
                    "isblocked": this.state.isblocked,
                    "year": this.state.Myyear,
                    "month": this.state.Mymonth,
                    "day": this.state.Myday,
                    "hour": this.state.Myhour
                }
            },
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Content-Type': 'application/json;charset=UTF-8',
            }
        }).then(response => {
            console.log(response)
            if (response.data.errors) {
                this.setState({
                    error: response.data.errors
                })
            } else {
                window.location.reload();
                this.setState({
                    error: {},
                    firstName: "",
                    lastName: "",
                    age: 0,
                })
            }
        }).catch(error => {
            console.log('Error: ', error);
        });
    }

    deleteVisit = (e) => {
        e.preventDefault();
        //  e.stopPropagation();
        //e.nativeEvent.stopImmediatePropagation();
        const axios = require('axios').default;
        axios({
            method: 'delete',
            url: 'http://localhost:8080/visit/delete/' + this.state.id,
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Content-Type': 'application/json;charset=UTF-8',
            }
        }).then(response => {
            e.preventDefault();

            console.log(response)
            if (response.data.errors) {
                this.setState({
                    error: response.data.errors
                })
            } else {
                window.location.reload();
                this.setState({
                    error: {},
                    firstName: "",
                    lastName: "",
                    age: 0,
                })
            }
        }).catch(error => {
            console.log('Error: ', error);
        });
    }

    render() {
        const DisplayError = (error) => {
            return Object.keys(error.children).map((item, index) => {
                console.log(item, index)
                return (
                    <Alert key={index} variant="danger">
                        {error.children[item]}
                    </Alert>
                )
            })
        }

        const DisplayItems = (data) => {
            const list = data.children.map(item => <CustomItem key={item.id}>{item}</CustomItem>)
            return (
                <ListGroup variant="flush">
                    {list}
                </ListGroup>
            )
        }


        const CustomItem = (data) => {
            const item = data.children;
            return <ListGroup>
                <Col className="text-primary ms-3 p-2 fs-3"><b>Návštěva č.{item.id}</b></Col>

                <ListGroup.Item><b> Jméno:</b> {item.name}</ListGroup.Item>
                <ListGroup.Item><b> Příjmení:</b> {item.lastName}</ListGroup.Item>
                <ListGroup.Item><b> Kód pojištěnce:</b> {item.insurance_code}</ListGroup.Item>
                <ListGroup.Item><b> Email:</b> {item.email}</ListGroup.Item>
                <ListGroup.Item><b> Telefon:</b> {item.phone}</ListGroup.Item>
                <ListGroup.Item><b> Důvod:</b> {item.description}</ListGroup.Item>
                <ListGroup.Item><b> Popis důvodu:</b> {item.descriptionReason}</ListGroup.Item>
                <ListGroup.Item><b> Rok:</b> {item.year}</ListGroup.Item>
                <ListGroup.Item><b> Měsíc:</b> {item.month}</ListGroup.Item>
                <ListGroup.Item><b> Den:</b> {item.day}</ListGroup.Item>
                <ListGroup.Item><b> Hodina:</b> {item.hour}</ListGroup.Item>
            </ListGroup>


        };

        return (
            <Container>
                <Container className="text-center">


                    <h1 className='m-5'>Rezervujte si termín prohlídky!</h1>


                    <Button className='btn-reservation btn-primary mb-5 fs-5 p-3' href='#rezervace'
                            variant='primary'> Rezervovat termín! </Button>


                </Container>
                <Image className='mt-5 mb-5' src={reservationImg}></Image>

                <section id="rezervace">
                    <h1 className="text-primary"><b>Rezervace pacientů</b></h1>

                    <Card>
                        <Card.Body>
                            <Card.Title className="text-primary fs-3">Vytvořeni nové rezervace</Card.Title>
                            <Form onSubmit={this.createNew}>
                                <Form.Group className="mb-3" controlId="firstNameId">
                                    <Form.Label>Jméno</Form.Label>
                                    <Form.Control
                                        type="text"
                                        placeholder="Vložte jméno"
                                        value={this.state.name}
                                        onChange={(e) => this.onChange(e.target.value, 'name')}
                                    />
                                </Form.Group>

                                <Form.Group className="mb-3" controlId="lastNameId">
                                    <Form.Label>Příjmení</Form.Label>
                                    <Form.Control
                                        type="text"
                                        placeholder="Vložte příjmení"
                                        value={this.state.lastName}
                                        onChange={(e) => this.onChange(e.target.value, 'lastName')}
                                    />
                                </Form.Group>

                                <Form.Group className="mb-3" controlId="emailId">
                                    <Form.Label>Email</Form.Label>
                                    <Form.Control
                                        type="text"
                                        placeholder="Vložte email"
                                        value={this.state.email}
                                        onChange={(e) => this.onChange(e.target.value, 'email')}
                                    />
                                </Form.Group>

                                <Form.Group className="mb-3" controlId="phoneId">
                                    <Form.Label>Telefon</Form.Label>
                                    <Form.Control
                                        type="text"
                                        placeholder="Vložte telefon"
                                        value={this.state.phone}
                                        onChange={(e) => this.onChange(e.target.value, 'phone')}
                                    />
                                </Form.Group>

                                <Form.Group className="mb-3" controlId="codeId">
                                    <Form.Label>Kód pojištovny</Form.Label>
                                    <Form.Control
                                        type="text"
                                        placeholder="Vložte kod pojišťovny"
                                        value={this.state.insurance_code}
                                        onChange={(e) => this.onChange(e.target.value, 'insurance_code')}
                                    />
                                </Form.Group>


                                <Form.Group className="mb-3" controlId="reasonNameID">
                                    <Form.Label>Důvod návštevy</Form.Label>
                                    <Form.Control
                                        type="text"
                                        placeholder="Vlože název důvodu"
                                        value={this.state.name2}
                                        onChange={(e) => this.onChange(e.target.value, 'name2')}
                                    />
                                </Form.Group>
                                <Form.Group className="mb-3" controlId="descriptionID">
                                    <Form.Label>Upřesnění návštevy</Form.Label>
                                    <Form.Control
                                        type="text"
                                        placeholder=""
                                        value={this.state.description}
                                        onChange={(e) => this.onChange(e.target.value, 'description')}
                                    />
                                </Form.Group>

                                <Form.Group className="mb-3">
                                    <Form.Label>Rok</Form.Label>
                                    <Form.Select
                                        defaultValue={'DEFAULT'}
                                        onChange={(e) => this.onChange(e.target.value, 'Myyear')}>
                                        <option value="DEFAULT" disabled>Vyberte rok</option>
                                        <option value="2022">2022</option>
                                        <option value="2023">2023</option>
                                        <option value="2024">2024</option>
                                        <option value="2025">2025</option>

                                    </Form.Select>
                                </Form.Group>

                                <Form.Group className="mb-3">
                                    <Form.Label>Měsíc</Form.Label>
                                    <Form.Select defaultValue={'DEFAULT'}
                                                 onChange={(e) => this.onChange(e.target.value, 'Mymonth')}>
                                        <option value="DEFAULT" disabled>Vyberte měsíc</option>
                                        <option>1</option>
                                        <option>2</option>
                                        <option>3</option>
                                        <option>4</option>
                                        <option>5</option>
                                        <option>6</option>
                                        <option>7</option>
                                        <option>8</option>
                                        <option>9</option>
                                        <option>10</option>
                                        <option>11</option>
                                        <option>12</option>

                                    </Form.Select>
                                </Form.Group>

                                <Form.Group className="mb-3">
                                    <Form.Label>Den</Form.Label>
                                    <Form.Select defaultValue={'DEFAULT'}
                                                 onChange={(e) => this.onChange(e.target.value, 'Myday')}>
                                        <option value="DEFAULT" disabled>Vyberte den</option>
                                        <option>1</option>
                                        <option>2</option>
                                        <option>3</option>
                                        <option>4</option>
                                        <option>5</option>
                                        <option>6</option>
                                        <option>7</option>
                                        <option>8</option>
                                        <option>9</option>
                                        <option>10</option>
                                        <option>11</option>
                                        <option>12</option>
                                        <option>13</option>
                                        <option>14</option>
                                        <option>15</option>
                                        <option>16</option>
                                        <option>17</option>
                                        <option>18</option>
                                        <option>19</option>
                                        <option>20</option>
                                        <option>21</option>
                                        <option>22</option>
                                        <option>23</option>
                                        <option>24</option>
                                        <option>25</option>
                                        <option>26</option>
                                        <option>27</option>
                                        <option>28</option>
                                        <option>29</option>
                                        <option>30</option>
                                        <option>31</option>

                                    </Form.Select>
                                </Form.Group>

                                <Form.Group className="mb-3">
                                    <Form.Label>Čas</Form.Label>
                                    <Form.Select defaultValue={'DEFAULT'}
                                                 onChange={(e) => this.onChange(e.target.value, 'Myhour')}>

                                        <option value="DEFAULT" disabled>Vyberte čas</option>
                                        <option>8:00:00</option>
                                        <option>9:00:00</option>
                                        <option>10:00:00</option>
                                        <option>11:00:00</option>
                                        <option>12:00:00</option>
                                        <option>13:00:00</option>
                                        <option>14:00:00</option>
                                        <option>15:00:00</option>
                                        <option>16:00:00</option>
                                        <option>17:00:00</option>
                                        <option>18:00:00</option>

                                    </Form.Select>

                                </Form.Group>

                                <Button variant="primary" type="submit">Vytvořit rezervaci</Button>
                            </Form>

                            <Form onSubmit={this.deleteVisit}>
                                <Form.Group className="mt-3" controlId="firstNameId">
                                    <FormLabel>Smazání návštevy podle čísla návštevy</FormLabel>
                                    <Form.Control

                                        type="text"
                                        placeholder="Vložte číslo návštevy"
                                        value={this.state.id}
                                        onChange={(e) => this.onChange(e.target.value, 'id')}
                                    />
                                </Form.Group>
                                <Button variant="danger" className="mt-2" type="submit">Smazat rezervaci</Button>
                            </Form>
                        </Card.Body>
                    </Card>

                    <Card>
                        <DisplayError>{this.state.error}</DisplayError>
                        <DisplayItems>{this.state.data}</DisplayItems>
                    </Card>
                </section>


            </Container>
        );
    }
}

export default App;