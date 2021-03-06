import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {RoomService} from './room.service';
import {MatDialog, MatSnackBar, MatTableDataSource} from '@angular/material';
import {Room} from '../room';
import {PostDialogComponent} from '../post-dialog/post-dialog.component';

@Component({
  selector: 'app-room-list',
  templateUrl: './room-list.component.html',
  styleUrls: ['./room-list.component.css']
})
export class RoomListComponent implements OnInit {
  rooms: MatTableDataSource<Room> = new MatTableDataSource();
  displayedColumns = ['id', 'name', 'area', 'cube', 'light', 'heating', 'edit', 'detail'];

  constructor(
    private roomService: RoomService,
    private router: Router,
    private route: ActivatedRoute,
    public dialog: MatDialog,
    private snackbar: MatSnackBar
  ) { }

  ngOnInit() {
    this.getRooms();
  }

  getRooms(): void {
    this.roomService.getRooms(
      this.route.params['value'].id,
      this.route.params['value'].floorId
    ).subscribe(resp => {
      this.rooms.data = resp;
    });
  }

  goBack(): void {
    this.router.navigate(['buildings', this.route.params['value'].id, 'floors', this.route.params['value'].floorId]);
  }

  onDetailClick(room: Room): void {
    this.router.navigate(['buildings', this.route.params['value'].id, 'floors', this.route.params['value'].floorId, 'rooms', room.roomId]);
  }

  onEditClick(room: Room): void {
    this.createEditRoomDialog(room);
  }

  createPostRoomDialog(): void {
    const dialogRef = this.dialog.open(PostDialogComponent, {
      width: '250px',
      data: { type: 'Room', name: '', area: '', cube: '', light: '', heating: '', text: 'Create'}
    });

    dialogRef.afterClosed().subscribe(result => {
      this.roomService.createRoom(this.route.params['value'].id, this.route.params['value'].floorId, {
        roomName: result.name,
        area: result.area,
        cube: result.cube,
        light: result.light,
        heating: result.heating
      })
        .subscribe(resp => {
          this.snackbar.open('Successfully created', '', {
            duration: 1500
          });
        }, err => {
          this.snackbar.open('Could not create', '', {
            duration: 1500
          });
        }, () => {
          this.getRooms();
        });
    });
  }

  createEditRoomDialog(room: Room): void {
    const dialogRef = this.dialog.open(PostDialogComponent, {
      width: '250px',
      data: { type: 'Room', name: room.roomName, area: room.area, cube: room.cube, light: room.light, heating: room.heating, text: 'Update'}
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      this.roomService.updateRoom(this.route.params['value'].id, this.route.params['value'].floorId, room.roomId,{
        roomName: result.name,
        area: result.area,
        cube: result.cube,
        light: result.light,
        heating: result.heating
      })
        .subscribe(resp => {
          this.snackbar.open('Successfully updated', '', {
            duration: 1500
          });
        }, err => {
          this.snackbar.open('Could not update', '', {
            duration: 1500
          });
        }, () => {
          this.getRooms();
        });
    });
  }

}
