import { Component, OnInit } from '@angular/core';
import {MatDialog, MatSnackBar, MatTableDataSource} from '@angular/material';
import {Floor} from '../floor';
import {FloorService} from './floor.service';
import {ActivatedRoute, Router} from '@angular/router';
import {PostDialogComponent} from '../post-dialog/post-dialog.component';

@Component({
  selector: 'app-floor-list',
  templateUrl: './floor-list.component.html',
  styleUrls: ['./floor-list.component.css']
})
export class FloorListComponent implements OnInit {
  floors: MatTableDataSource<Floor> = new MatTableDataSource();
  displayedColumns = ['id', 'name', 'area', 'cube', 'light', 'heating', 'detail'];

  constructor(
    private floorService: FloorService,
    private router: Router,
    private route: ActivatedRoute,
    public dialog: MatDialog,
    private snackbar: MatSnackBar
  ) { }

  ngOnInit() {
    this.getFloors();
  }

  getFloors(): void {
    this.floorService.getFloors(this.route.params['value'].id).subscribe(resp => {
      this.floors.data = resp;
    });
  }

  goBack(): void {
    this.router.navigate(['buildings', this.route.params['value'].id]);
  }

  onDetailClick(floor: Floor): void {
    this.router.navigate(['buildings', this.route.params['value'].id, 'floors', floor.floorId]);
  }

  createFloorDialog(): void {
    const dialogRef = this.dialog.open(PostDialogComponent, {
      width: '250px',
      data: { type: 'Floor', name: ''}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      this.floorService.createFloor(this.route.params['value'].id, {floorName: result.name})
        .subscribe(resp => {
          this.snackbar.open('Successfully created', '', {
            duration: 1500
          });
        }, err => {
          this.snackbar.open('Could not create', '', {
            duration: 1500
          });
        }, () => {
          this.getFloors();
        });
    });
  }

}
