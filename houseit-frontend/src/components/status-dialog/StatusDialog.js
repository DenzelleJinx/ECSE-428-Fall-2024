import React from "react";
import Dialog from "@mui/material/Dialog";
import DialogContent from "@mui/material/DialogContent";
import DialogTitle from "@mui/material/DialogTitle";
import DialogActions from "@mui/material/DialogActions";
import Button from "@mui/material/Button";
import { green, red } from "@mui/material/colors";
import { DialogContentText } from "@mui/material";

const StatusDialog = ({ open, onClose, severity = 'error', title, message }) => {
    // Set the color and title of the dialog based on the severity
    const dialogTitle = title || (severity === 'success' ? 'Success' : 'An Error Occurred');
    const dialogColor = severity === 'success' ? green[500] : red[500];

    return (
        <Dialog open={open} onClose={onClose} aria-labelledby="status-dialog-title" aria-describedby="status-dialog-description">
            <DialogTitle id="status-dialog-title" sx={{ color: dialogColor }}>
                {dialogTitle}
            </DialogTitle>
            <DialogContent>
                <DialogContentText id="status-dialog-description">
                    {message || (severity === 'success' ? 'The operation was successful.' : 'An error occurred while processing your request.')}
                    </DialogContentText>
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose} color={severity === 'success' ? 'primary' : 'error'}>
                    Close
                </Button>
            </DialogActions>
        </Dialog>
    );
};

export default StatusDialog;