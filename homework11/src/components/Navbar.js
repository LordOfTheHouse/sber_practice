import "bootstrap/dist/css/bootstrap.css";
import { Navbar, Nav } from 'react-bootstrap';
export const NavbarHome = ({ isUser, setIsUser }) => {
    const stepHome = () => {
        setIsUser(false);
    };
    const stepUser = () => {
        setIsUser(true);
    };
    return (
            <Navbar bg="info" expand="lg">
                <Navbar.Brand onClick={stepHome}>Home</Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="ml-auto">
                        <Nav.Link onClick={stepUser}>Account</Nav.Link>
                    </Nav>
                </Navbar.Collapse>
            </Navbar>
    );
};
