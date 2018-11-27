import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';

@Component({
  selector: 'app-post-dialog',
  templateUrl: './post-dialog.component.html',
  styleUrls: []
})
export class PostDialogComponent {

  constructor(
    public dialogRef: MatDialogRef<PostDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

  onNoClick(): void {
    this.dialogRef.close();
  }

  isRoomDialog(): boolean {
    return this.data['type'] === 'Room';
  }

  isInvalid(): boolean {
    if (!this.isRoomDialog()) {
      return !(this.data['name'].length > 0);
    }
    return !(this.data['name'].length > 0 &&
      this.data['area'].length > 0 &&
      this.data['cube'].length > 0 &&
      this.data['light'].length > 0 &&
      this.data['heating'].length > 0);
  }

}
